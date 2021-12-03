let express = require("express"),
  app = express(),
  bodyParser = require("body-parser");

app.use(bodyParser.urlencoded({ extended: false }));

const fs = require("fs");

app.get("/tasks", function (req, res) {
  fs.readFile("./todo.json", "utf8", (err, jsonString) => {
    if (err) {
      console.log("Error reading file from disk:", err);
      return;
    }
    try {
      const data = JSON.parse(jsonString);
      res.send(data);
    } catch (err) {
      console.log("Error parsing JSON string:", err);
    }
  });
});

app.get("/tasks/new", function (req, res) {
  let id = req.query.id;
  let task = req.query.task;

  if (id != undefined && task != undefined) {
    fs.readFile("./todo.json", "utf8", (err, jsonString) => {
      if (err) {
        console.log("Error reading file from disk:", err);
        return;
      }
      try {
        const data = JSON.parse(jsonString);
        if (data[id]) {
          console.log("id already exist");
          res.redirect("/tasks");
          return;
        }
        console.log("Data before: ", data);
        data[id] = task;
        console.log("Data after: ", data);
        fs.writeFile("./todo.json", JSON.stringify(data), (err) => {
          if (err) {
            console.log("Error writing file:", err);
          } else {
            res.redirect("/tasks");
          }
        });
      } catch (err) {
        console.log("Error parsing JSON string:", err);
      }
    });
  } else {
    res.redirect("/tasks");
  }
});

app.get("/tasks/remove", function (req, res) {
  let id = req.query.id;
  console.log("id: ", id);

  if (id != undefined) {
    fs.readFile("./todo.json", "utf8", (err, jsonString) => {
      if (err) {
        console.log("Error reading file from disk:", err);
        return;
      }
      try {
        const data = JSON.parse(jsonString);
        console.log("Data before: ", data);
        delete data[id];
        console.log("Data after: ", data);
        fs.writeFile("./todo.json", JSON.stringify(data), (err) => {
          if (err) {
            console.log("Error writing file:", err);
          } else {
            res.redirect("/tasks");
          }
        });
      } catch (err) {
        console.log("Error parsing JSON string:", err);
      }
    });
  } else {
    res.redirect("/tasks");
  }
});

const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
  console.log(`app is running on port ${PORT}`);
});
