package com.intenthq.gander.utils

import org.slf4j._
import java.text.MessageFormat

/**
 * Trait that enables logging. String formatting is based on the Java MessageFormat object, NOT the
 * regular String.format.  See this documentation:
 * http://download.oracle.com/javase/1.4.2/docs/api/java/text/MessageFormat.html
 *
 * The code was initially taken from this location at Stack Overflow:
 * From http://stackoverflow.com/questions/978252/logging-in-scala/981942#981942
 */
trait Logging {
  val logger: Logger = Logging.getLogger(this)

  private def formatmsg(msg: String, refs: Seq[Any]): String = new MessageFormat(msg).format(refs.toArray)

  private def checkFormat(msg: String, refs: Seq[Any]): String = if (refs.nonEmpty) formatmsg(msg, refs) else msg

  def trace(msg: String, refs: Any*) { logger trace checkFormat(msg, refs) }

  def trace(t: Throwable, msg: String, refs: Any*) { logger trace(checkFormat(msg, refs), t) }

  def info(msg: String, refs: Any*) { logger info checkFormat(msg, refs) }

  def info(t: Throwable, msg: String, refs: Any*) { logger info (checkFormat(msg, refs), t) }

  def warn(msg: String, refs: Any*) { logger warn checkFormat(msg, refs) }

  def warn(t: Throwable, msg: String, refs: Any*) { logger warn (checkFormat(msg, refs), t) }

  def critical(msg: String, refs: Any*) { logger error checkFormat(msg, refs) }

  def critical(t: Throwable, msg: String, refs: Any*) { logger error (checkFormat(msg, refs), t) }

  def debug(msg: String, refs: Any*) { logger debug checkFormat(msg, refs) }

  def debug(t: Throwable, msg: String, refs: Any*) { logger debug (checkFormat(msg, refs), t) }
}

/**
 * Note: implementation taken from scalax.logging API
 */
object Logging {
  def loggerNameForClass(className: String) = className.stripSuffix("$")

  def getLogger(logging: AnyRef) = LoggerFactory.getLogger(loggerNameForClass(logging.getClass.getName))
}
