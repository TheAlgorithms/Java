import java.util.Scanner;
class Scratch {
    public static int rooms_available,private_room;
    public static boolean x;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("**********\n\t SMART CITY PROJECT\n***********");
        // The program is designed for making an ease for the tourist
        System.out.println("(WELCOME TO PAKISTAN, FOLLOWING IS THE FACILITY GIVEN TO YOU) Enter :\n1) '1' for Hotel reservation\n2) '2' of Booking transport and visiting places" +
                "\n3) '3' for Confirming your return Ticket");
        String valid="start";
        int tourist = s.nextInt();
        String rooms="closed";
        if (tourist == 1) {
            switch (tourist) {
                case 1:
                    while (valid.equals("start")) {

                        int hotel_reservation;
                        if (rooms.equals("e")){   // Will be entered when we have to decide numbers of rooms in a certain hostel
                            System.out.println("The number of rooms available are: "+rooms_available);
                            // percentage for the number availability
                            int room_02=(int)(rooms_available*0.30);
                            int room_03=(int)(rooms_available*0.50);
                            int room_04=(int)(rooms_available*0.20);
                            int net_rooms=room_02+room_03+room_04;

                            if (net_rooms<rooms_available){
                                private_room=rooms_available-net_rooms;
                                System.out.println("Since the private rooms are '"+private_room+"'");
                            }
                            System.out.println("So 2 sited rooms are \""+room_02+"\"");
                            System.out.println("So 3 sited rooms are \""+room_03+"\"");
                            System.out.println("So 4 sited rooms are \""+room_04+"\"");
                            System.out.println("Enter following commands for the registration:");
                            System.out.println("'1' For the private room");
                            System.out.println("'2' for 2 sited rooms");
                            System.out.println("'3' for 3 sited rooms");
                            System.out.println("'4' for 4 sited rooms");

                            int rooms_validation=s.nextInt();
                            if ((rooms_validation==2)||(rooms_validation==3)||(rooms_validation==4) || (rooms_validation==1)){

                                if (rooms_validation==1){
                                    x=false;

                                    int [] private_r=new int[private_room];
                                    for (int i=0; private_room>i;i++){
                                        int new_value=(int)(Math.random()*10);
                                        private_r[i]=new_value;

                                        for (int j=0;i>j;j++){
                                            if (private_r[i]==private_r[j]){
                                                x=true;
                                            }
                                        }
                                        if (x==true) {
                                            i = i - 1;
                                        }
                                    }
                                    for (int i=0;private_room>i;i++){
                                        System.out.println("Which room do you want to register:");
                                        System.out.print(private_r[i]+"");
                                    }


                                } else if (rooms_validation==2) {

                                    x=false;

                                    int [] room2_r=new int[room_02];
                                    for (int i=0; room_02>i;i++){
                                        int new_value=(int)((Math.random()*35)+11);
                                        room2_r[i]=new_value;

                                        for (int j=0;i>j;j++){
                                            if (room2_r[i]==room2_r[j]){
                                                x=true;
                                            }
                                        }
                                        if (x==true) {
                                            i = i - 1;
                                        }
                                    }
                                    for (int i=0;room_02>i;i++){
                                        System.out.print(room2_r[i]+"  ");
                                    }
                                    System.out.println("Enter the room number for registeration:");
                                    int room_num=s.nextInt();
                                    for (int i=0; room_02>i;i++){
                                        if (room2_r[i]==room_num){
                                            System.out.println("Your room number:"+room_num+" has been registered");
                                            return;
                                        }
                                    }
                                    System.out.println("Room not available");

                                    /*
                                    Now we will proceed for the 3 seated rooms registeration
                                     */



                                } else if (rooms_validation==3) {



                                    x=false;

                                    int [] room3_r=new int[room_03];
                                    for (int i=0; room_03>i;i++){
                                        int new_value=(int)((Math.random()*70)+0);
                                        room3_r[i]=new_value;

                                        for (int j=0;i>j;j++){
                                            if (room3_r[i]==room3_r[j]){
                                                x=true;
                                            }
                                        }
                                        if (x==true) {
                                            i = i - 1;
                                        }
                                    }
                                    for (int i=0;room_03>i;i++){
                                        System.out.print(room3_r[i]+"  ");
                                    }
                                    System.out.println("Enter the room number for registeration:");
                                    int room_num=s.nextInt();
                                    for (int i=0; room_03>i;i++){
                                        if (room3_r[i]==room_num){
                                            System.out.println("Your room number:"+room_num+" has been registered");
                                            return;
                                        }
                                    }
                                    System.out.println("Room not available");
                                }
                                else if (rooms_validation==4) {

                                    x=false;

                                    int [] room4_r=new int[room_04];
                                    for (int i=0; room_04>i;i++){
                                        int new_value=(int)((Math.random()*100)+0);
                                        room4_r[i]=new_value;

                                        for (int j=0;i>j;j++){
                                            if (room4_r[i]==room4_r[j]){
                                                x=true;
                                            }
                                        }
                                        if (x==true) {
                                            i = i - 1;
                                        }
                                    }
                                    for (int i=0;room_04>i;i++){
                                        System.out.print(room4_r[i]+"  ");
                                    }
                                    System.out.println("Enter the room number for registeration:");
                                    int room_num=s.nextInt();
                                    for (int i=0; room_04>i;i++){
                                        if (room4_r[i]==room_num){
                                            System.out.println("Your room number:"+room_num+" has been registered");
                                            return;
                                        }
                                    }
                                    System.out.println("Room not available");
                                }
                                else {

                                }
                            }
                            else{
                                System.out.println("You entered Invalid number "+rooms_validation);
                            }
                            break;
                        }
                        System.out.println("(Following are some hotels catagery) Enter:\n1) '1' for the 5 star \n2) '2' for the 4 star\n3) '3' for the 3 star ");
                        System.out.println();
                        int user = s.nextInt();

                        switch (user) {

                            case 1:
                                System.out.println("You choosed 5 star");
                                System.out.println(
                                        "\n" + "Royal Motel Guest House (0),Empire Hotel (1),The 108 Hotel (2)");
                                //Making an storing array:
                                System.out.println("Given respective number for reservation");
                                String [] hotel={"Royal Motel Guest House","Empire Hotel","The 108 Hotel"};
                                hotel_reservation=s.nextInt();
                                if ((hotel_reservation==0) || (hotel_reservation==1) || (hotel_reservation==2)){
                                    System.out.println("You choose the hotel "+hotel[hotel_reservation]);
                                    rooms_available=(int)(Math.random()*31);
                                    if (rooms_available==0){        //No room available
                                        System.out.println("Sorry no rooms available are: "+rooms_available);
                                        valid="end";
                                    }
                                    else {
                                        rooms="e";
                                        continue;

                                    }
                                }
                                else {
                                    System.out.println("Invalid value given");
                                    valid="Stop";
                                    break;
                                }
                                break;
                            case 2:
                                System.out.println("You choosed 4 star");
                                System.out.println("\n" + "Legend Hotel Islamabad 4 stars (0),Ramada by Wyndham Islamabad  (1),Al-khalid hotel  (2)");
                                //Making an storing array:
                                System.out.println("Given respective number for reservation");
                                String [] hotel_4_star={"Legend Hotel Islamabad","Ramada by Wyndham Islamabad","Al-khalid hotel "};
                                hotel_reservation=s.nextInt();
                                if ((hotel_reservation==0) || (hotel_reservation==1) || (hotel_reservation==2)){
                                    System.out.println("You choose the hotel "+hotel_4_star[hotel_reservation]);
                                    rooms_available=(int)(Math.random()*31);
                                    if (rooms_available==0){        //No room available
                                        System.out.println("Sorry no rooms available are: "+rooms_available);
                                        valid="end";
                                    }
                                    else {
                                        rooms="e";
                                        continue;

                                    }
                                }
                                else {
                                    System.out.println("Invalid value given");
                                    valid="Stop";
                                    break;
                                }
                                break;

                            case 3:
                                System.out.println("You choosed 3 star");
                                System.out.println("\n" + " Hotel Crown Plaza Islamabad (0),  Roomy Signature Hotel Islamabad (1),Hotel Margala(2)");
                                //Making an storing array:
                                System.out.println("Given respective number for reservation");
                                String [] hotel_3_star={"Hotel Crown Plaza Islamabad","Roomy Signature Hotel Islamabad","Hotel Margala"};
                                hotel_reservation=s.nextInt();
                                if ((hotel_reservation==0) || (hotel_reservation==1) || (hotel_reservation==2)){
                                    System.out.println("You choose the hotel "+hotel_3_star[hotel_reservation]);
                                    rooms_available=(int)(Math.random()*31);
                                    if (rooms_available==0){        //No room available
                                        System.out.println("Sorry no rooms available are: "+rooms_available);
                                        valid="end";
                                    }
                                    else {
                                        rooms="e";
                                        continue;

                                    }
                                }
                                else {
                                    System.out.println("Invalid value given");
                                    valid="Stop";
                                    break;
                                }


                                break;
                            default:
                                System.out.println("Invalid number");
                                System.out.println("Last chance else will be no result:");
                                System.out.println("(Following are some hotels catagery) Enter:\n1) '1' for the 5 star \n2) '2' for the 4 star\n3) '3' for the 3 star ");
                                int user1 = s.nextInt();
                                if ((user1 == 0) || (user1 == 1) || (user1 == 2)) {
                                    System.out.println("OKS ENTER AGAIN");

                                }
                                else
                                    valid="error";
                                break;
                        }// Switch case ends here
                    }//while ends here

            }
        }
        // For traveling facilities
        else if (tourist==2) {

        }
        else if (tourist==3){
            long cnic=(int)((Math.random()*999999999)+000000000);
            System.out.println("Program for the verification of the person identity:" +cnic);

            System.out.println("Enter your CNIC number:");
            long cnic_store=s.nextLong();
            if (cnic==cnic_store) {
                String[] names = {"khalid", "ahmad", "ali", "hashim", "rida", "saira"};
                int lengh_array = names.length;
                System.out.println(lengh_array);
                int orignal_num = lengh_array - 1;
                int rand_for_name = (int) ((Math.random() * orignal_num) + 0);
                String store_name = names[rand_for_name];
                System.out.println("Welcome " + store_name + " ");
                int rand_password = (int) ((Math.random() * 9999) + 0000);
                System.out.println(rand_password);
                System.out.println("Enter the desired password 4 digits");
                int user_password = s.nextInt();


                if (rand_password == user_password) {
                    int rand_money = (int) ((Math.random() * 100000000) + 1000000);
                    int number = 5;
                    int value_submit = 1;
                    System.out.println("Enter your name");
                    System.out.println("Hope that you have a good trip:\n Enter your response:");
                    for (int i = 0; number >= i; i++) {
                        System.out.print(value_submit + ")");
                        for (int j = 0; i > j; j++) {
                            System.out.print("*");
                        }
                        value_submit = value_submit + 1;
                        System.out.println();
                    }
                    int user_submit = s.nextInt();
                    if ((user_submit >= 1) && (user_submit <= 6)) {
                        System.out.println("Ok! Your Response have been submitted");
                    } else {
                        System.out.println("Invalid number entered");
                    }
                    System.out.println("Enter your country name:");
                    String country = s.next();

                    int ticket_amount = (int) ((Math.random() * 1000000) + 100000);
                    System.out.println("Your Ticket amount is: " + ticket_amount);
                    System.out.println("Enter '1' for confirming payment\n'2' to decline ");
                    System.out.println("Your Account limit is " + rand_money);
                    int amount = s.nextInt();
                    if (amount == 1) {
                        if (ticket_amount <= rand_money) {
                            System.out.println("Your seat have been confirmed");
                        } else {
                            System.out.println("Invalid amount in the wallet");
                        }
                    } else if (amount == 2) {
                        System.out.println("You declined the payment");
                    } else {
                        System.out.println("Invalid number entered");
                    }
                }
            }
        }
    }
}
