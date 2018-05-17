var layout = {
  title: "YAW QUADCOPTER",
  xaxis: {
    title: "WAKTU",
    titlefont: {
     // family: "Courier New, monospace",
      size: 18,
      color: "#3eae40"
    }
  },
  yaxis: {
    title: "NILAI YAW",
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
var initGraphOptions = {layout: layout, fileopt : "extend", filename : "yawBaru5"};

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
	  console.log('Yaw:' + d.demo.rotation.yaw);
	  console.log('i: ' + i );
      var streamObject = JSON.stringify({ x : i, y : d.demo.rotation.yaw });
      stream1.write(streamObject+'\n');
      i++;
    }
  }
});
		
	});
  });