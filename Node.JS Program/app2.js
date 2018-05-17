var arDrone = require('ar-drone');
var client = arDrone.createClient();

/*
client.after(1000, function() {
    this.takeoff();
  })
client.after(2000, function() {
    this.stop();
  })
client.after(500, function() {
    this.front(0.03);
  })
client.after(500, function() {
    this.stop();
  })
*/



var SerialPort = require("serialport");
var serialport = new SerialPort("COM9");
serialport.on('open', function(){
	console.log('Serial Port Opened');
//	client.on('navdata', console.log); data yang ada 0.07
	serialport.on('data', function(data){
		console.log(data[0]);
	if(data[0] == 1){
	console.log("Drone Terbang\n");
	client.takeoff();
	client.disableEmergency();
	}
	else if(data[0]== 2){
	console.log("Drone Stop\n");
    client.land();
	}
	else if(data[0] == 3){
	client.up(0.15);
	console.log("Drone Ke atas\n");
	}
		else if(data[0] == 4){
	client.down(0.15);
	console.log("Drone Ke bawah\n");
	}
	else if(data[0] == 5){
	client.left(0.07);
	console.log("Drone Ke kiri\n");
	}
	else if(data[0]== 6){
	client.right(0.07);
	console.log("Drone Ke kanan\n");
	}
	else if(data[0]== 7){
	client.front(0.07);
	console.log("Drone Ke depan\n");
	}
	else if(data[0]== 8){
	client.back(0.07);
	console.log("Drone Ke belakang\n");
	}
	else if(data[0]== 11){
	client.clockwise(0.5);
	console.log("Putar Ke kanan\n");
	}
	else if(data[0]== 12){
	client.counterClockwise(0.5);
	console.log("Putar Ke kiri\n");
	}
	
	else if(data[0]== 44){
	console.log("Drone Hover\n");
	client.stop();
	}
	
	
	
	else if(data[0]== 30){
	client.stop();
	}
	
	/*
	else if(data[0]== 9){
	client.animate('yawDance', 1000);
	console.log("Drone Joget\n");
	}*/
  });
});