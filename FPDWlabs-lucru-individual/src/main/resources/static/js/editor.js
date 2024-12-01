document.addEventListener("DOMContentLoaded", () => {
  const toggleButton = document.getElementById("toggle-response");
  const answerInput = document.getElementById("answer-input");
  const codeEditor = document.getElementById("code-editor");
  const lineNumbers = document.querySelector(".line-numbers");
  const codeContent = document.querySelector(".code-content");

  // Generate line numbers
  function generateLineNumbers() {
    const totalLines = codeContent.textContent.split("\n").length;
    lineNumbers.innerHTML = "";
    for (let i = 1; i <= totalLines; i++) {
      const line = document.createElement("div");
      line.textContent = i;
      lineNumbers.appendChild(line);
    }
  }

  // Initial line numbers
  generateLineNumbers();

// Toggle between text input and code editor
toggleButton.addEventListener("click", () => {
    if (codeEditor.classList.contains("hidden")) {
        codeEditor.classList.remove("hidden");
        answerInput.classList.add("hidden"); // Adaugă clasa "hidden" la answerInput
        toggleButton.textContent = "Switch to Text";
    } else {
        codeEditor.classList.add("hidden");
        answerInput.classList.remove("hidden");
        toggleButton.textContent = "Switch to Code";
    }
});

  // Handle keydown for the code editor
  codeContent.addEventListener("keydown", (e) => {
    if (e.key === "Enter") {
      e.preventDefault();
      document.execCommand("insertLineBreak");
      generateLineNumbers();
    }
  });

  // Auto-adjust line numbers on any input
  codeContent.addEventListener("input", () => {
    generateLineNumbers();
    highlightSyntax(codeContent); // Call syntax highlighting function
  });
});

function highlightSyntax(element) {
  // Simple syntax highlighting (replace with a more robust solution if needed)
  const keywords = [
    "if",
    "else",
    "for",
    "while",
    "function",
    "var",
    "let",
    "const",
    "class",
  ];
  const code = element.textContent;
  let highlightedCode = code.replace(
    /\b(if|else|for|while|function|var|let|const|class)\b/g,
    '<span class="keyword">$1</span>'
  );
  element.innerHTML = highlightedCode;

  // Setează cursorul la sfârșitul textului
  const range = document.createRange();
  const sel = window.getSelection();
  range.setStart(element, element.childNodes.length);
  range.collapse(true);
  sel.removeAllRanges();
  sel.addRange(range);
  1;
}
