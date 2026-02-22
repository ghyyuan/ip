### 1. Refining Tagging Feature & Defensive Programming

**Tool Used:** Google Gemini

**Task:** Refactoring the `addTag` method in the `Task` class to handle edge cases, prevent storage delimiter injection, and improve code architecture.

**How I used AI:**
After refactoring the `Parser` to act as a strict gatekeeper for user inputs, I realized the `addTag` method in `Task.java` had redundant checks. 
I proposed a specific architectural change to AI: replacing the `isEmpty` check with an `assert` (since Parser already guarantees non-empty strings) and adding a specific exception for commas (`,`) to protect the Storage file's delimiter. 
I asked the AI to validate this logic and generate the updated code.

**Observations & Learnings:**
* **What worked:** The AI was excellent at validating software engineering concepts. It perfectly understood my intent and framed it.
* **Productivity & Time Saved:** It saved me a great amount of time. AI immediately confirmed that my design intuition was correct.
* **Overall impact:** Using AI as an architectural sounding board rather than just a code generator is effective. It helps consolidate SE concepts while keeping the codebase clean and robust.

### 2. GUI Customization and Layout (JavaFX)

**Tool Used:** Google Gemini

**Task:** Enhancing the graphical user interface by setting the application title, adding a background image, and fixing layout distortion when the window is maximized.

**How I used the AI:**
Based on what I observed during manual testing, 
I asked the AI how to change the application title to "Memo", how to apply a background image to the chat interface, and where to insert `setMaximized(false)` to prevent the UI from breaking when resized.

**Observations & Learnings:**
* **What worked:** The AI provided exact locations to modify both Java code and FXML code.
* **Course Correction:** The AI corrected my assumption about using `setMaximized(false)`. It explained that `setResizable(false)` is the proper way to lock a window size. 
* **Productivity & Time Saved:** It saved me from digging through documentation and wrestling with FXML layout properties and CSS syntax.

### 3. Code Documentation (Javadoc) and A-JUnit Evaluation

**Tool Used:** Google Gemini

**Task:** Generating standard Javadocs for various methods and evaluating whether my existing JUnit tests (`ParserTest` and `ToDoTest`) met the module's "A-JUnit" requirement.

**How I used the AI:**
I provided some specific methods to the AI and asked it to generate Javadocs. Then, I asked it to act as an evaluator to review my existing test classes and tell me if they were considered too trivial for the A-JUnit standard.

**Observations & Learnings:**
* **What worked:** The AI generated formatted Javadocs, eliminating the tedious boilerplate typing.
* **Critical Evaluation:** The AI provided a highly valuable critique of my tests. It validated that my `ParserTest` was good, but correctly pointed out that my `ToDoTest` was too trivial because it only tested basic string formatting with no edge cases or logic branches.
* **Leveling Up:** To meet the "non-trivial" requirement, AI guided me to write tests for `TaskList` (testing array out-of-bounds exceptions) and `Storage` (testing File I/O using JUnit 5's `@TempDir` extension). 
* This improved my understanding of what makes a unit test valuable and taught me advanced JUnit 5 features for safe file system testing.
