Atlassian Asignment. 

Task: Please write a solution that takes a chat message string and returns a JSON string containing information about its contents. Special content to look for includes:
	1. @mentions - A way to mention a user. Always starts with an '@' and ends when hitting a non-word character. (http://help.hipchat.com/knowledgebase/articles/64429-how-do-mentions-work-)
	2. Emoticons - For this exercise, you only need to consider 'custom' emoticons which are alphanumeric strings, no longer than 15 characters, contained in parenthesis. You can assume that anything 		matching this format is an emoticon. (https://www.hipchat.com/emoticons)
	3. Links - Any URLs contained in the message, along with the page's title.



Solution:

   The solution was to use a StringTokenizer in order to get all the different tokens from the Message, and then usinn a Lexical Analyzer COncept identify the tokens, create a object containing all this information and export as a JSON format.
   

Android App Arch.:

  - MVVM Application Arch.  - Usign android data binding and RX. 
  - Libraries :
	- ANdroid Support Libraries
	- GSON LIbrary to extract a JSON Representation from a object..
	- Parceler to save the viewModel instance.
	- RxAndroid to use the Reactive Programming concept.
 



  
Running Tests: 
	- Go to the test package and run the MessageLexicalAnaliyserUnitTest test file.


Installing the app:
	- YOu can either clone the repository or  <a href="https://github.com/skyguydaa7/AtlassianLexAnalyzer/blob/master/apk/atlassianassigment.apk?raw=true">-Download the apk file here.</a>
  
   





