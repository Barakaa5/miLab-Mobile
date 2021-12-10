let express = require("express"),
  app = express(),
  bodyParser = require("body-parser");

app.use(bodyParser.urlencoded({ extended: false }));

const fs = require("fs");


app.get("/", function (req, res) {
  res.send("Go to '/files/:fileName' to download the desired file");
});

app.get("/files/:fileName", function (req, res) {
  let fileName = req.params.fileName;

  if (["file1", "file2", "file3", "file4"].includes(fileName)) {
    let readStream = fs.createReadStream("" + fileName + ".txt");
    readStream.pipe(res);
  } else {
    res.send(
      "File don't exist, please choose existing file: file1 / file2 / file3 / file4"
    );
  }
});

const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
  console.log(`app is running on port ${PORT}`);
});
