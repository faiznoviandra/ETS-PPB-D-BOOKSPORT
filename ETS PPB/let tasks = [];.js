let tasks = [];

const form = document.getElementById("task-form");
const list = document.getElementById("task-list");
const sortOption = document.getElementById("sort-option");

form.addEventListener("submit", function(e) {
  e.preventDefault();
  const title = document.getElementById("task-title").value;
  const deadline = document.getElementById("task-deadline").value;

  tasks.push({
    title,
    deadline,
    completed: false
  });

  form.reset();
  renderTasks();
});

sortOption.addEventListener("change", renderTasks);

function renderTasks() {
  const sortedTasks = [...tasks];
  if (sortOption.value === "deadline") {
    sortedTasks.sort((a, b) => new Date(a.deadline) - new Date(b.deadline));
  } else if (sortOption.value === "status") {
    sortedTasks.sort((a, b) => a.completed - b.completed);
  }

  list.innerHTML = "";
  sortedTasks.forEach((task, index) => {
    const li = document.createElement("li");
    li.className = task.completed ? "completed" : "";

    const label = document.createElement("label");
    const checkbox = document.createElement("input");
    checkbox.type = "checkbox";
    checkbox.checked = task.completed;
    checkbox.addEventListener("change", () => {
      task.completed = checkbox.checked;
      renderTasks();
    });

    label.appendChild(checkbox);
    label.appendChild(document.createTextNode(" " + task.title + " (Deadline: " + new Date(task.deadline).toLocaleString() + ")"));

    const delBtn = document.createElement("button");
    delBtn.textContent = "Hapus";
    delBtn.style.marginLeft = "10px";
    delBtn.style.backgroundColor = "#e53935";
    delBtn.style.color = "white";
    delBtn.addEventListener("click", () => {
      tasks.splice(index, 1);
      renderTasks();
    });

    li.appendChild(label);
    li.appendChild(delBtn);
    list.appendChild(li);
  });
}
