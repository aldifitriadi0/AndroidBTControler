#include <SoftwareSerial.h>
SoftwareSerial BT(10, 11);
char Command[100];
int totalCom = 0;
int hoverstat = 88;
int randoms = 0;
int simpan1 = 0;
int simpan2 = 0;
int simpan3 = 0;
int simpan4 = 0;
int simpan5 = 0;
int awal = 1;

void setup() {
  BT.begin(9600);
  Serial.begin(9600);
  //Serial.println("NYALA");
}


void loop() {
  while (BT.available()) {
    char c = BT.read();
    //Serial.println(c);
    delay(10);
    if (c != '0') {
      Command[totalCom] = c ;
      totalCom++;
    }
  }


  if (totalCom > 0) {

    for (int i = 0; i < totalCom; i++) {
      Serial.println(Command[i]);
      if (Command[i + 2] == 'K') {
        if (Command[i + 2] == 'K' && Command[i + 4] == 'K') {

        }
        else if (Command[i + 2] == 'K' && Command[i + 5] == 'K') {

        }

        if (Command[i] == 'J') {
          int class2 = (int)Command[i + 1] - 48;
          simpan1 = (int)class2;
          simpan2 = (int)Command[i + 3] - 48;
          randoms = 3;
          int hasilrandom = random(awal, randoms);
          delay(15);
          if (hasilrandom == 1) {
            Serial.println("random 1 atau");
            if (Command[i + 1] == '1' || Command[i + 1] == '2' || Command[i + 1] == '3' || Command[i + 1] == '4' ||
                Command[i + 1] == '5' || Command[i + 1] == '6' || Command[i + 1] == '7' || Command[i + 1] == '8') {
              if (class2 % 2 == 0) {
                delay(12);
                Serial.write((int)class2 - 1);
                delay(2501);
                Serial.write(30);
                delay(1512);
              }
              else {
                delay(12);
                Serial.write((int)class2 + 1);
                delay(2501);
                Serial.write(30);
                delay(1512);
              }

              Command[i] = 0;
              delay(10);
              Command[i + 1] = 0;
              delay(10);
              Command[i + 2] = 0;
              delay(10);
              Command[i + 3] = 0;
              delay(10);
              Serial.write(30);
              delay(1512);
            }
          }
          else if (hasilrandom == 2) {
            Serial.println("random2 atau");
            if (Command[i + 3] == '1' || Command[i + 3] == '2' || Command[i + 3] == '3' || Command[i + 3] == '4' ||
                Command[i + 3] == '5' || Command[i + 3] == '6' || Command[i + 3] == '7' || Command[i + 3] == '8') {
              if (class2 % 2 == 0) {
                delay(12);
                Serial.write(simpan2 - 1);
                delay(2501);
                Serial.write(30);
                delay(1512);
              }
              else {
                delay(12);
                Serial.write(simpan2 + 1);
                delay(2501);
                Serial.write(30);
                delay(1512);
              }
              Serial.write(simpan2);
              delay(2500);
              Command[i] = 0;
              delay(10);
              Command[i + 1] = 0;
              delay(10);
              Command[i + 2] = 0;
              delay(10);
              Command[i + 3] = 0;
              delay(10);
              Serial.write(30);
              delay(1512);
            }
          }
        }
      }

      else if (Command[i] == 'E') {
        int classS = (int)Command[i + 1] - 48;
        if (Command[i + 1] == '3' || Command[i + 1] == '4' || Command[i + 1] == '5' || Command[i + 1] == '6' || Command[i + 1] == '7' || Command[i + 1] == '8') {
          delay(12);
          Serial.write((int)classS);
          // Serial.println("1 Detik");
          delay(1000);
          Command[i + 1] = 0;
          i = i + 1;
          Serial.write(30);
          delay(1512);
        }
      }

      else if (Command[i] == 'F') {
        int classS = (int)Command[i + 1] - 48;
        if (Command[i + 1] == '3' || Command[i + 1] == '4' || Command[i + 1] == '5' || Command[i + 1] == '6' || Command[i + 1] == '7' || Command[i + 1] == '8') {
          delay(12);
          Serial.write((int)classS);
          // Serial.println("2 Detik");
          delay(2000);
          Command[i + 1] = 0;
          i = i + 1;
          Serial.write(30);
          delay(1512);
        }
      }

      else if (Command[i] == 'G') {
        int classS = (int)Command[i + 1] - 48;
        if (Command[i + 1] == '3' || Command[i + 1] == '4' || Command[i + 1] == '5' || Command[i + 1] == '6' || Command[i + 1] == '7' || Command[i + 1] == '8') {
          delay(12);
          Serial.write((int)classS);
          // Serial.println("3 Detik");
          delay(3010);
          Command[i + 1] = 0;
          i = i + 1;
          Serial.write(30);
          delay(1512);
        }
      }

      else if (Command[i] == 'H') {
        int classS = (int)Command[i + 1] - 48;
        if (Command[i + 1] == '3' || Command[i + 1] == '4' || Command[i + 1] == '5' || Command[i + 1] == '6' || Command[i + 1] == '7' || Command[i + 1] == '8') {
          delay(12);
          Serial.write((int)classS);
          // Serial.println("4 detik");
          delay(4000);
          Command[i + 1] = 0;
          i = i + 1;
          Serial.write(30);
          delay(1512);
        }
      }

      else if (Command[i] == 'I') {
        int classS = (int)Command[i + 1] - 48;
        if (Command[i + 1] == '3' || Command[i + 1] == '4' || Command[i + 1] == '5' || Command[i + 1] == '6' || Command[i + 1] == '7' || Command[i + 1] == '8') {
          delay(12);
          Serial.write((int)classS);
          // Serial.println("5 Detik");
          delay(5000);
          Command[i + 1] = 0;
          i = i + 1;
          Serial.write(30);
          delay(1512);
        }
      }

      else if (Command[i] == 'J') {
        // Serial.println("Masok");
        int class2 = (int)Command[i + 1] - 48;
        //Serial.println(Command[i]);
        // Serial.println((int)class2);
        //Serial.println((int)Command[i + 1]);
        if (Command[i + 1] == '1' || Command[i + 1] == '2' || Command[i + 1] == '3' || Command[i + 1] == '4' ||
            Command[i + 1] == '5' || Command[i + 1] == '6' || Command[i + 1] == '7' || Command[i + 1] == '8') {
          if (class2 % 2 == 0) {
            delay(12);
            Serial.write((int)class2 - 1);
            // Serial.println("Negasi");
            delay(2501);
            Command[i + 1] = 0;
            i = i + 1;
            Serial.write(30);
            delay(1512);
          }
          else {
            delay(12);
            Serial.write((int)class2 + 1);
            // Serial.println("Negasi");
            delay(2501);
            Command[i + 1] = 0;
            i = i + 1;
            Serial.write(30);
            delay(1512);
          }
        }
      }

      else if (Command[i] == 'C') {// Perintah sedikit
        int classS = (int)Command[i + 1] - 48;
        if (Command[i + 1] == '3' || Command[i + 1] == '4' || Command[i + 1] == '5' || Command[i + 1] == '6' || Command[i + 1] == '7' || Command[i + 1] == '8') {
          delay(12);
          Serial.write((int)classS);
          //  Serial.println("Sedikit");
          delay(1000);
          Command[i + 1] = 0;
          i = i + 1;
          Serial.write(30);
          delay(1512);
        }
      }

      else if (Command[i] == 'D') {// Perintah Banyak
        int classS = (int)Command[i + 1] - 48;
        if (Command[i + 1] == '3' || Command[i + 1] == '4' || Command[i + 1] == '5' || Command[i + 1] == '6' || Command[i + 1] == '7' || Command[i + 1] == '8') {
          delay(12);
          Serial.write((int)classS);
          //  Serial.println("Banyak");
          delay(5000);
          Command[i + 1] = 0;
          i = i + 1;
          Serial.write(30);
          delay(1512);
        }
      }
      else if (Command[i] == '9') {// Perintah putar
        int classS = (int)Command[i + 1] - 48;
        if (classS == 5) {
          //  Serial.println("Putar Kiri");
          delay(12);
          Serial.write(12);
          delay(2500);
          Command[i] = 0;
          Command[i + 1] = 0;
          i = i + 1;
          Serial.write(30);
          delay(1512);
        }
        else if (classS == 6) {
          //  Serial.println("Putar Kanan");
          delay(12);
          Serial.write(11);
          delay(2500);
          Command[i] = 0;
          Command[i + 1] = 0;
          i = i + 1;
          Serial.write(30);
          delay(1512);
        }
      }

      else if (Command[i] == '1' || Command[i] == '2' || Command[i] == '3' || Command[i] == '4' ||
               Command[i] == '5' || Command[i] == '6' || Command[i] == '7' || Command[i] == '8') {
        int class1 = (int)Command[i] - 48;
        delay(12);
        if (Command[i + 1] == 'C') {
          delay(12);
          Serial.write((int)class1);
          //   Serial.println("Perintah Sedikit");
          delay(1000);
          Command[i] = 0;
          Command[i + 1] = 0;
          i = i + 1;
          Serial.write(30);
          delay(1512);
        }

        else if (Command[i + 1] == 'D') {
          delay(12);
          Serial.write((int)class1);
          //   Serial.println("Perintah Banyak");
          delay(5000);
          Command[i] = 0;
          Command[i + 1] = 0;
          i = i + 1;
          Serial.write(30);
          delay(1512);
        }

        else if (Command[i + 1] == 'E') {
          delay(12);
          Serial.write((int)class1);
          //   Serial.println("Perintah 1");
          delay(1000);
          Command[i] = 0;
          Command[i + 1] = 0;
          i = i + 1;
          Serial.write(30);
          delay(1512);
        }

        else if (Command[i + 1] == 'F') {
          delay(12);
          Serial.write((int)class1);
          //   Serial.println("Perintah 2");
          delay(2000);
          Command[i] = 0;
          Command[i + 1] = 0;
          i = i + 1;
          Serial.write(30);
          delay(1512);
        }

        else if (Command[i + 1] == 'G') {
          delay(12);
          Serial.write((int)class1);

          //Serial.println("Perintah 3");
          delay(3000);
          Command[i] = 0;
          Command[i + 1] = 0;
          i = i + 1;
          Serial.write(30);
          delay(1512);
        }

        else if (Command[i + 1] == 'H') {
          delay(12);
          Serial.write((int)class1);
          //   Serial.println("Perintah 4");
          delay(4000);
          Command[i] = 0;
          Command[i + 1] = 0;
          i = i + 1;
          Serial.write(30);
          delay(1512);
        }

        else if (Command[i + 1] == 'I') {
          delay(12);
          Serial.write((int)class1);
          //   Serial.println("Perintah 5");
          delay(5000);
          Command[i] = 0;
          Command[i + 1] = 0;
          i = i + 1;
          Serial.write(30);
          delay(1512);
        }

        else if (Command[i + 1] == 'I') {
          delay(12);
          Serial.write((int)class1);
          //   Serial.println("Perintah 5");
          delay(5000);
          Command[i] = 0;
          Command[i + 1] = 0;
          i = i + 1;
          Serial.write(30);
          delay(1512);
        }

        else if (Command[i + 1] == 'K') {
          delay(12);
          if (Command[i + 1] == 'K' && Command[i + 3] == 'K' && Command[i + 5] == 'K') {
            simpan1 = (int)class1;
            simpan2 = (int)Command[i + 2] - 48;
            simpan3 = (int)Command[i + 4] - 48;
            simpan4 = (int)Command[i + 6] - 48;
            randoms = 5;
            int hasilrandom = random(awal, randoms);
            delay(15);
            if (hasilrandom == 1) {
              //       Serial.println("Random 4.1");
              Serial.write(simpan1);
              delay(2500);
              Command[i] = 0;
              delay(10);
              Command[i + 1] = 0;
              delay(10);
              Command[i + 2] = 0;
              delay(10);
              Command[i + 3] = 0;
              delay(10);
              Command[i + 4] = 0;
              delay(10);
              Command[i + 5] = 0;
              delay(10);
              Command[i + 6] = 0;
              Serial.write(30);
              delay(1512);
            }
            else if (hasilrandom == 2) {
              //       Serial.println("Random 4.2");
              Serial.write(simpan2);
              delay(2500);
              Command[i] = 0;
              delay(10);
              Command[i + 1] = 0;
              delay(10);
              Command[i + 2] = 0;
              delay(10);
              Command[i + 3] = 0;
              delay(10);
              Command[i + 4] = 0;
              delay(10);
              Command[i + 5] = 0;
              delay(10);
              Command[i + 6] = 0;
              Serial.write(30);
              delay(1512);
            }
            else if (hasilrandom == 3) {
              //       Serial.println("Random 4.3");
              Serial.write(simpan3);
              delay(2500);
              Command[i] = 0;
              delay(10);
              Command[i + 1] = 0;
              delay(10);
              Command[i + 2] = 0;
              delay(10);
              Command[i + 3] = 0;
              delay(10);
              Command[i + 4] = 0;
              delay(10);
              Command[i + 5] = 0;
              delay(10);
              Command[i + 6] = 0;
              Serial.write(30);
              delay(1512);
            }
            else if (hasilrandom == 4) {
              //       Serial.println("Random 4.4");
              Serial.write(simpan4);
              delay(2500);
              Command[i] = 0;
              delay(10);
              Command[i + 1] = 0;
              delay(10);
              Command[i + 2] = 0;
              delay(10);
              Command[i + 3] = 0;
              delay(10);
              Command[i + 4] = 0;
              delay(10);
              Command[i + 5] = 0;
              delay(10);
              Command[i + 6] = 0;
              Serial.write(30);
              delay(1512);
            }
          }
          else if (Command[i + 1] == 'K' && Command[i + 3] == 'K') {
            simpan1 = (int)class1;
            simpan2 = (int)Command[i + 2] - 48;
            simpan3 = (int)Command[i + 4] - 48;
            randoms = 4;
            int hasilrandom = random(awal, randoms);
            delay(15);
            if (hasilrandom == 1) {
              Serial.write(simpan1);
              delay(2500);
              Command[i] = 0;
              delay(10);
              Command[i + 1] = 0;
              delay(10);
              Command[i + 2] = 0;
              delay(10);
              Command[i + 3] = 0;
              delay(10);
              Command[i + 4] = 0;
              Serial.write(30);
              delay(1512);
            }
            if (hasilrandom == 2) {
              Serial.write(simpan2);
              delay(2500);
              Command[i] = 0;
              delay(10);
              Command[i + 1] = 0;
              delay(10);
              Command[i + 2] = 0;
              delay(10);
              Command[i + 3] = 0;
              delay(10);
              Command[i + 4] = 0;
              Serial.write(30);
              delay(1512);
            }
            else if (hasilrandom == 3) {
              Serial.write(simpan3);
              delay(2500);
              Command[i] = 0;
              delay(10);
              Command[i + 1] = 0;
              delay(10);
              Command[i + 2] = 0;
              delay(10);
              Command[i + 3] = 0;
              delay(10);
              Command[i + 4] = 0;
              Serial.write(30);
              delay(1512);
            }
          }
          else if (Command[i + 1] == 'K') {
            simpan1 = (int)class1;
            simpan2 = (int)Command[i + 2] - 48;
            randoms = 3;

            int hasilrandom = random(awal, randoms);
            delay(15);
            if (hasilrandom == 1) {
              Serial.write(simpan1);
              delay(2500);
              Command[i] = 0;
              delay(10);
              Command[i + 1] = 0;
              delay(10);
              Command[i + 2] = 0;
              Serial.write(30);
              delay(1512);
            }
            else if (hasilrandom == 2) {
              Serial.write(simpan2);
              delay(2500);
              Command[i] = 0;
              delay(10);
              Command[i + 1] = 0;
              delay(10);
              Command[i + 2] = 0;
              delay(10);
              Serial.write(30);
              delay(1512);
            }
          }
        }

        else {
          Serial.write((int)class1);
          //   Serial.println("Gerakan 11 Dasar");
          delay(2501);
          if (Command[i] == '1') {
            delay(3010);
          }
          Serial.write(30);
          delay(1512);
          if (Command[i] == '2') {
            delay(3010);
            hoverstat = 1;
          }
        }
      }
    }

    if (hoverstat != 1) {
      Serial.write(44);
    }
    totalCom = 0;
  }
  hoverstat = 6;
}
