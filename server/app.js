const { json } = require("body-parser");

let express = require("express"),
  app = express(),
  request = require("request"),
  bodyParser = require("body-parser");

app.use(bodyParser.urlencoded({ extended: false }));

function parse(Symbol) {
  return new Promise(function (resolve, reject) {
    var url = `https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=${Symbol}&interval=1min&apikey=95F0TJW7NMT5PW1K`;

    request.get(
      {
        url: url,
        json: true,
        headers: { "User-Agent": "request" },
      },
      (err, res, data) => {
        // in addition to parsing the value, deal with possible errors
        if (err) return reject(err);
        try {
          // JSON.parse() can throw an exception if not valid JSON
          resolve(Object.entries(data["Time Series (1min)"])[0][1]["4. close"]);
          // Object.entries(body["Time Series (1min)"])[0][1]["4. close"]
        } catch (e) {
          reject(e);
        }
      }
    );
  });
}

app.get("/", function (req, res) {
  res.send("move to '/:stock'");
});

app.get("/:stock", function (req, res) {
  let Symbol = req.params.stock;
  parse(Symbol)
    .then(function (StockPrice) {
      console.log(StockPrice);
      res.send(JSON.stringify({"price": StockPrice, "name": Symbol}));
      
    })
    .catch(function (err) {
      console.log(err);
    });
});

const PORT = process.env.PORT || 8080;
app.listen(PORT, () => {
  console.log(`app is running on port ${PORT}`);
});
