package com.lptemplatecompany.lptemplatedivision.lptemplateservicename

import cats.effect.{ ExitCode, IO, IOApp }
import cats.syntax.apply._
import com.lptemplatecompany.lptemplatedivision.lptemplateservicename.config.{ Config, Context }
import com.lptemplatecompany.lptemplatedivision.shared.interpreter.Info
import io.chrisdavenport.log4cats.Logger
import io.chrisdavenport.log4cats.slf4j.Slf4jLogger

object AppMain extends IOApp {

  override def run(args: List[String]): IO[ExitCode] =
    for {
      log <- Slf4jLogger.create[IO]
      outcome <- program(log).attempt
      code <- outcome.fold(
        e => log.error(s"Application failed: $e") *> IO(ExitCode.Error),
        _ => log.info("Application terminated with no error indication") *> IO(ExitCode.Success)
      )
    } yield code

  private def program(log: Logger[IO]): IO[Unit] =
    for {
      cfg <- Config.load
      info <- Info.of[IO, Config](cfg, log)
      _ <- info.logEnvironment
      _ <- log.info(cfg.toString)
      outcome <- runApp(cfg, log)
    } yield outcome

  /**
   * All resources, such as temporary directories and the expanded files, are cleaned up when no longer
   * required. This is implemented using `cats.effect.Resource`.
   */
  private def runApp(cfg: Config, log: Logger[IO]): IO[Unit] =
    Context.create(cfg, log).use {
      ctx =>
        ctx.service.run
    }

}
