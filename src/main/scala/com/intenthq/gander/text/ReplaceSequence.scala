package com.intenthq.gander.text

/**
* Wraps the usage of making multiple string replacements in an ordered sequence.
* For Example... instead of doing this over and over:</p>
* <blockquote>
*   <pre>
*     String text = "   Some example text     ";
*     text = text.{@link String#replaceAll(String, String) replaceAll}("e", "E");
*     text = text.{@link String#replaceAll(String, String) replaceAll}(" ", "_");
*     text = text.{@link String#replaceAll(String, String) replaceAll}("^\\s+$", "");
*   </pre>
* </blockquote>
* You can use a <code>ReplaceSequence</code> like this:</p>
* <blockquote>
*   <pre>
*     static final betterReplacements = ReplaceSequence.{@link #create(String, String) create}("e", "E").{@link #append(String, String) append}(" ", "_").{@link #append(String) append}("^\\s+$");
*
*     void fixMyString(String text) {
*       return betterReplacements.{@link #replaceAll(String) replaceAll}(text);
*     }
*   </pre>
* </blockquote>
*
* Internally, an ordered list of {@link Matcher}s and its associated replacement is built as the {@link #append} method is called.<br/>
* Each matcher is {@link Matcher#reset(CharSequence) reset} with the input specified in the {@link #replaceAll(String)} method.</p>
* Use of this class can improve performance if the sequence of replacements is intended to be used repeatedly throughout the life of an application.<br/>
* This is due to the fact that each {@link Pattern} is only compiled once and each {@link Matcher} is only generated once.
*/
object ReplaceSequence {
  /**
  * Creates a new <code>ReplaceSequence</code> with the first pattern to be replaced with the specified <code>replaceWith</code> parameter.
  * @param firstPattern The regex {@link Pattern pattern} {@link String} for the first replacement
  * @param replaceWith The {@link String} to replace matches of the specified pattern
  * @return a new instance
  */
  def apply(firstPattern: String, replaceWith: String = ""): ReplaceSequence =
    new ReplaceSequence(List(StringReplacement(firstPattern, replaceWith)))
}

class ReplaceSequence(replacements: List[StringReplacement]) {

  /**
  * Appends a new pattern to this instance in a builder pattern
  * @param pattern The regex {@link Pattern pattern} {@link String} for this replacement
  * @param replaceWith The {@link String} to replace matches of the specified pattern
  * @return this instance of itself for use in a builder pattern
  */
  def append(pattern: String, replaceWith: String = ""): ReplaceSequence =
    new ReplaceSequence(StringReplacement(pattern, replaceWith) :: replacements)

  /**
  * Applies each of the replacements specified via the initial {@link #create(String)} and/or any additional via {@link #append(String)}
  * @param input the {@link String} to apply all of the replacements to
  * @return the resulting {@link String} after all replacements have been applied
  */
  def replaceAll(input: String): String = replacements.foldLeft(input)((str, converter) => converter.replaceAll(str))
}
