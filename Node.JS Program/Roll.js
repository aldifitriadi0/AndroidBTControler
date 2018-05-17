var layout = {
  title: "ROLL QUADCOPTER",
  xaxis: {
    title: "WAKTU",
    titlefont: {
     // family: "Courier New, monospace",
      size: 18,
      color: "#3eae40"
    }
  },
  yaxis: {
    title: "NILAI ROLL",
    titlefont: {
     // family: "Courier New, monospace",
      size: 18,
      color: "#3eae40"
    }
  }
};

var arDrone = require('ar-drone'); 
var plotly = require('plotly')('faviansyah1','LmCIIrjyo4SuEhO6EW5E');
var client = new arDrone.createClient();
var initData = [{x:[], y:[], stream:{token:'e3174eg1xn', maxpoints:200}}];
var initGraphOptions = {layout: layout, fileopt : "extend", filename : "RollBaru20"};

var lastNavDataMs = 0;



plotly.plot(initData, initGraphOptions, function (err, msg) {

	
  if (err) return console.log(err)
  console.log(msg);

  var stream1 = plotly.stream('e3174eg1xn', function (err, res) {
    console.log(err, res);
    clearInterval(loop,1000); // once stream is closed, stop writing
  });

  
  var i = 0;
  var loop = setInterval(function () {
	  client.on('navdata', function(d) {
  var nowMs = new Date().getTime();
  if (nowMs - lastNavDataMs > 100) {
    lastNavDataMs = nowMs;
    // Process navdata once per second.
    if(d.demo){
	  console.log('Roll:' + d.demo.rotation.roll);
	  console.log('i: ' + i );
      var streamObject = JSON.stringify({ x : i, y : d.demo.rotation.roll });
      stream1.write(streamObject+'\n');
      i++;
    }
  }
});
		
	});
  });