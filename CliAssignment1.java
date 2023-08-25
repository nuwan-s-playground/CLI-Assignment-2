import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;;

public class CliAssignment1 {
    private final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        final String CLEAR = "\033[H\033[2J";
        final String LIGHT_BLUE_COLOR_BOLD = "\033[36;1m";
        final String PURPLE_COLOR_BOLD = "\033[35;1m";
        final String GREEN_COLOR_BOLD = "\033[32;1m";
        final String WHITE_COLOR_BOLD = "\033[37;1m";
        final String BLUE_COLOR_BOLD = "\033[34;1m";
        final String YELLOW_COLOR_BOLD = "\033[33;1m";
        final String RED_COLOR_BOLD = "\033[31;1m";

        final String RESET = "\033[0m";

        final String DASHBOARD = "Smart Banking System";
        final String CREATE_ACCOUNT = "Create New Account";
        final String DEPOSITE = "Deposit";
        final String WITHDRAWAL = "Withdrawal";
        final String TRANSFER = "Transfer";
        final String PRINT_STATEMENT = "Print Statement";
        final String DELETE_ACOOUNT = "Delete Account";
        String screen = DASHBOARD;
        ArrayList<Integer> idArray = new ArrayList<>();
        ArrayList<Double> balanceArray = new ArrayList<>();
        String[] nameArray = new String[0];
        int idnum;

        boolean valid = true;
        boolean twoValidation = false;
        String[][] details = new String[0][3];
        String name;
        double initialDepost;
        String id;

        do {
            String customerID;
            final String APP_TITLE = String.format("%s%s%s", GREEN_COLOR_BOLD, screen, RESET);

            System.out.println(CLEAR);
            System.out.printf("%s%s%s \n", GREEN_COLOR_BOLD, "-".repeat(100), RESET);
            System.out.printf("%s%s%s \n", " ".repeat((100 - APP_TITLE.length()) / 2), APP_TITLE,
                    " ".repeat((100 - APP_TITLE.length()) / 2));
            System.out.printf("%s%s%s \n\n", GREEN_COLOR_BOLD, "-".repeat(100), RESET);
            switch (screen) {
                case DASHBOARD: {

                    System.out.printf("%s%s%s \n", BLUE_COLOR_BOLD, "[1].Create New Account", RESET);
                    System.out.printf("%s%s%s \n", BLUE_COLOR_BOLD, "[2].Deposit", RESET);
                    System.out.printf("%s%s%s \n", BLUE_COLOR_BOLD, "[3].Withdrawal", RESET);
                    System.out.printf("%s%s%s \n", BLUE_COLOR_BOLD, "[4].Transfer", RESET);
                    System.out.printf("%s%s%s \n", BLUE_COLOR_BOLD, "[5].Print Statement", RESET);
                    System.out.printf("%s%s%s \n", BLUE_COLOR_BOLD, "[6].Delete Account", RESET);
                    System.out.printf("%s%s%s \n\n", BLUE_COLOR_BOLD, "[7].Exit", RESET);

                    System.out.printf("%s%s%s%s", WHITE_COLOR_BOLD, "Enter an option to continue : ", RESET,
                            LIGHT_BLUE_COLOR_BOLD);

                    int option = scanner.nextInt();
                    scanner.nextLine();
                    switch (option) {
                        case 1: {
                            screen = CREATE_ACCOUNT;
                            break;
                        }
                        case 2: {
                            screen = DEPOSITE;
                            break;
                        }
                        case 3: {
                            screen = WITHDRAWAL;
                            break;
                        }
                        case 4: {
                            screen = TRANSFER;
                            break;
                        }
                        case 5: {
                            screen = PRINT_STATEMENT;
                            break;
                        }
                        case 6: {
                            screen = DELETE_ACOOUNT;
                            break;
                        }
                        case 7: {
                            System.exit(0);
                            break;
                        }
                        default:
                            continue;

                    }
                    break;
                }
                case CREATE_ACCOUNT: {

                    idnum = idArray.size() + 1;
                    valid = false;
                    idArray.add(idArray.size(), idArray.size() + 1);
                    id = String.format("SDB-%05d", (details.length + 1));
                    System.out.println(id);
                    System.out.printf("%sID\t\t: %s%sSDB-%05d %s \n", WHITE_COLOR_BOLD, RESET, LIGHT_BLUE_COLOR_BOLD,
                            (details.length + 1), RESET);

                    loop: do {
                        valid = false;

                        System.out.printf("%sName\t\t: %s%s", WHITE_COLOR_BOLD, RESET, LIGHT_BLUE_COLOR_BOLD);
                        name = scanner.nextLine().strip();
                        if (name.isBlank()) {
                            System.out.printf("%sName can't be empty%s\n", RED_COLOR_BOLD, RESET);
                            valid = true;
                            continue;
                        }
                        for (int index = 0; index < name.length(); index++) {
                            if (!(Character.isLetter(name.charAt(index))
                                    || Character.isSpaceChar(name.charAt(index)))) {
                                System.out.printf("%sInvalid Name%s\n", RED_COLOR_BOLD, RESET);
                                valid = true;
                                continue loop;
                            }
                        }

                    } while (valid);

                    do {
                        twoValidation = false;
                        System.out.printf("%sInitial Deposit\t:%s%s ", WHITE_COLOR_BOLD, RESET, LIGHT_BLUE_COLOR_BOLD);
                        initialDepost = scanner.nextDouble();
                        scanner.nextLine();
                        // todo : check the balance are numbers

                        if (initialDepost < 5000) {
                            System.out.printf("%sInsufficient Amount%s\n", RED_COLOR_BOLD, RESET);
                            twoValidation = true;
                            continue;
                        }

                    } while (twoValidation);
                    String[][] newDetails = new String[details.length + 1][3];

                    for (int i = 0; i < details.length; i++) {
                        for (int index = 0; index < 3; index++) {
                            newDetails[i][index] = details[i][index];
                        }

                    }

                    newDetails[newDetails.length - 1][0] = id;
                    newDetails[newDetails.length - 1][1] = name;
                    newDetails[newDetails.length - 1][2] = String.valueOf(initialDepost);
                    details = newDetails;
                    System.out.println(Arrays.toString(details[details.length - 1]));
                    System.out.printf("\n%sID : %s%s Name : %s has been created successfully. %s\n\n",
                            GREEN_COLOR_BOLD, "SDK-",
                            id, details[details.length - 1][1], RESET);

                    System.out.printf("%sDo you want to add new student (Y/n)?  : %s%s ", WHITE_COLOR_BOLD, RESET,
                            LIGHT_BLUE_COLOR_BOLD);
                    String secondOption = scanner.nextLine().strip().toUpperCase();
                    if (secondOption.equals("N")) {
                        screen = CREATE_ACCOUNT;
                        valid = true;
                        break;

                    } else {
                        screen = DASHBOARD;
                        valid = true;
                        break;
                    }

                }
                case DEPOSITE: {
                    do {
                        System.out.printf("%sEnter A/C No: \t\t: %s%s", WHITE_COLOR_BOLD, RESET, LIGHT_BLUE_COLOR_BOLD);
                        String accounntNumber = scanner.nextLine();
                        boolean validateId = true;
                        int indexOfId = 0;

                        for (int indexOfAaNumber : idArray) {
                            if ((String.format("SDB-%05d", indexOfAaNumber).equals(accounntNumber))) {
                                validateId = false;
                                break;

                            }
                            indexOfId++;
                        }
                        if (validateId == true) {
                            System.out.printf("%sInvalid ID number.%s\n", RED_COLOR_BOLD, RESET);
                            continue;
                        }
                        ;
                        System.out.printf("%sCurrent Balance: \t: %s%s%s \n", WHITE_COLOR_BOLD, RESET,
                                LIGHT_BLUE_COLOR_BOLD, details[indexOfId][2]);
                        System.out.printf("%sDeposite Ammount: \t: %s%s", WHITE_COLOR_BOLD, RESET,
                                LIGHT_BLUE_COLOR_BOLD);
                        Double depositeAmmount = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.printf("%sNew Balance: \t\t: %s%s%.2f\n", WHITE_COLOR_BOLD, RESET,
                                LIGHT_BLUE_COLOR_BOLD,
                                (depositeAmmount + Double.valueOf(details[indexOfId][2])));
                        details[indexOfId][2] = String.valueOf(depositeAmmount + Double.valueOf(details[indexOfId][2]));
                        System.out.printf("%sDo you want to continue (Y/n): \t\t: %s%s", WHITE_COLOR_BOLD, RESET,
                                LIGHT_BLUE_COLOR_BOLD);
                        valid = false;
                        String thirdOption = scanner.nextLine().strip().toUpperCase();
                        if (thirdOption.equals("N")) {
                            screen = DEPOSITE;
                            valid = true;
                            break;

                        } else {
                            screen = DASHBOARD;
                            valid = true;
                            break;
                        }

                    } while (valid);

                }
                case WITHDRAWAL: {
                    do {
                        System.out.printf("%sEnter A/C No: \t\t: %s%s", WHITE_COLOR_BOLD, RESET, LIGHT_BLUE_COLOR_BOLD);
                        String accounntNumber = scanner.nextLine();
                        boolean validateId = true;
                        int indexOfId = 0;

                        for (int indexOfAaNumber : idArray) {
                            if ((String.format("SDB-%05d", indexOfAaNumber).equals(accounntNumber))) {
                                validateId = false;
                                break;

                            }
                            indexOfId++;
                        }
                        if (validateId == true) {
                            System.out.printf("%sInvalid ID number.%s\n", RED_COLOR_BOLD, RESET);
                            continue;
                        }
                        ;
                        System.out.printf("%sCurrent Balance: \t: %s%s%s \n", WHITE_COLOR_BOLD, RESET,
                                LIGHT_BLUE_COLOR_BOLD, details[indexOfId][2]);

                        boolean validationOfWithdrawal;
                        Double withdrawalAmount;

                        do {

                            validationOfWithdrawal = false;
                            System.out.printf("%sWithdrawal Ammount: \t: %s%s", WHITE_COLOR_BOLD, RESET,
                                    LIGHT_BLUE_COLOR_BOLD);
                            withdrawalAmount = scanner.nextDouble();
                            scanner.nextLine();
                            if (Double.valueOf(details[indexOfId][2]) - withdrawalAmount < 500) {
                                System.out.printf("Insuficiant balance.", RED_COLOR_BOLD, RESET);
                                valid = true;
                                screen = DASHBOARD;

                            }
                            if (withdrawalAmount < 100) {
                                System.out.printf("%sMinimun withdrawal amount is Rs.100.%s\n", RED_COLOR_BOLD, RESET);
                                validationOfWithdrawal = true;
                            }

                        } while (validationOfWithdrawal);

                        System.out.printf("%sNew Balance: \t\t: %s%s%.2f\n", WHITE_COLOR_BOLD, RESET,
                                LIGHT_BLUE_COLOR_BOLD,
                                (Double.valueOf(details[indexOfId][2]) - withdrawalAmount));
                        details[indexOfId][2] = String
                                .valueOf(Double.valueOf(details[indexOfId][2]) - withdrawalAmount);
                        System.out.printf("%sDo you want to continue (Y/n): \t\t: %s%s", WHITE_COLOR_BOLD, RESET,
                                LIGHT_BLUE_COLOR_BOLD);
                        valid = false;
                        String thirdOption = scanner.nextLine().strip().toUpperCase();
                        if (thirdOption.equals("N")) {
                            screen = WITHDRAWAL;
                            valid = true;
                            break;

                        } else {
                            screen = DASHBOARD;
                            valid = true;
                            break;
                        }

                    } while (valid);
                }
                case TRANSFER: {
                    
                        int indexOfId = 0;
                        int fronIndex;
                        int toIndex;
                        boolean validateId = true;
                        do{
                            System.out.printf("%sEnter from A/C No: \t\t: %s%s", WHITE_COLOR_BOLD, RESET,
                                LIGHT_BLUE_COLOR_BOLD);
                            String fromAccountNo = scanner.nextLine();
                            

                            for (int indexOfAcNumber : idArray) {
                                if ((String.format("SDB-%05d", indexOfAcNumber).equals(fromAccountNo))) {
                                    fronIndex = indexOfId;
                                    validateId = false;
                                    fronIndex = indexOfId;
                                    //break;

                                }
                                indexOfId++;
                            }
                            if (validateId == true) {
                                System.out.printf("%sInvalid %s number.%s\n", RED_COLOR_BOLD, fromAccountNo, RESET);
                                continue;
                            }
                        
                        } while (validateId);

                        do{
                            System.out.printf("%sEnter to A/C No: \t\t: %s%s", WHITE_COLOR_BOLD, RESET,
                                LIGHT_BLUE_COLOR_BOLD);
                            String toAccountNo = scanner.nextLine();
                        
                        
                            indexOfId = 0;
                            fronIndex=0;
                            validateId = true;
                            indexOfId = 0;
                            toIndex = 0;
                            for (int indexOfAcNumber : idArray) {
                                if ((String.format("SDB-%05d", indexOfAcNumber).equals(toAccountNo))) {
                                    indexOfAcNumber = indexOfId;
                                    validateId = false;
                                    toIndex = indexOfId;
                                    //break;

                                }
                                indexOfId++;
                            }
                            if (validateId == true) {
                                System.out.printf("%sInvalid ID number.%s\n", RED_COLOR_BOLD,  RESET);
                                continue;
                            }
                        } while (false);
                        System.out.printf("%sFrom A/C Name: \t\t\t: %s%s%s \n", WHITE_COLOR_BOLD, RESET,
                                LIGHT_BLUE_COLOR_BOLD,details[fronIndex][1]);

                        System.out.printf("%sFrom A/C Balance: \t\t: %s%s%.2f\n", WHITE_COLOR_BOLD, RESET,
                                LIGHT_BLUE_COLOR_BOLD,
                                (Double.valueOf(details[fronIndex][2])));
                        System.out.printf("%sTo A/C Name: \t\t\t: %s%s%s\n", WHITE_COLOR_BOLD, RESET,
                                LIGHT_BLUE_COLOR_BOLD,details[toIndex][1]);

                        System.out.printf("%sTo A/C Balance: \t\t: %s%s%.2f\n", WHITE_COLOR_BOLD, RESET,
                                LIGHT_BLUE_COLOR_BOLD,
                                (Double.valueOf(details[toIndex][2])));
                        boolean validationOfTransfer;
                        double transferAmount;
                        do {

                            validationOfTransfer = false;
                            System.out.printf("%sEnter transfer amount: \t\t\t: %s%s", WHITE_COLOR_BOLD, RESET,
                                    LIGHT_BLUE_COLOR_BOLD);
                            transferAmount = scanner.nextDouble();
                            scanner.nextLine();
                            if (Double.valueOf(details[fronIndex][2]) - transferAmount < 500) {
                                System.out.printf("Insuficiant balance.", RED_COLOR_BOLD, RESET);
                                valid = true;
                                screen = DASHBOARD;

                            }
                            if (transferAmount < 100) {
                                System.out.printf("%sMinimun transfer amount is Rs.100.%s\n", RED_COLOR_BOLD, RESET);
                                validationOfTransfer = true;
                            }

                        } while (validationOfTransfer);
                        System.out.printf("%sNew from A/C Balance: \t\t: %s%s%.2f\n", WHITE_COLOR_BOLD, RESET,
                                LIGHT_BLUE_COLOR_BOLD,
                                (Double.valueOf(details[fronIndex][2]) - transferAmount - 0.02 * transferAmount));
                        details[fronIndex][2] = String
                                .valueOf(Double.valueOf(details[fronIndex][2]) - transferAmount - 0.02 * transferAmount);
                        System.out.printf("%sNew to A/C Balance: \t\t: %s%s%.2f\n", WHITE_COLOR_BOLD, RESET,
                                LIGHT_BLUE_COLOR_BOLD,
                                (Double.valueOf(details[toIndex][2])));
                        details[toIndex][2]=String
                                .valueOf(Double.valueOf(details[toIndex][2]) );
                        System.out.printf("%sDo you want to continue (Y/n) \t: %s%s", WHITE_COLOR_BOLD, RESET,
                                LIGHT_BLUE_COLOR_BOLD);
                        valid = false;
                        String fourthOption = scanner.nextLine().strip().toUpperCase();
                        if (fourthOption.equals("N")) {
                            screen = TRANSFER;
                            valid = true;
                            break;

                        } else {
                            screen = DASHBOARD;
                            valid = true;
                            break;
                        }
                        
                        
                    

                }

            }

        } while (valid);
    }

    // public int[] accValidation(String fromAcc, String toAcc, int[] detailsArray) {
    //     int count = 0;
    //     int[] accNoValidationData= new int[2];
    //     for ( int indexOfAaNumber : detailsArray) {
    //         if ((String.format("SDB-%05d", indexOfAaNumber).equals(fromAcc))
    //                 || (String.format("SDB-%05d", indexOfAaNumber).equals(toAcc))) {
    //             int index1 = (String.format("SDB-%05d", indexOfAaNumber).equals(fromAcc)) ? 0 : 1;
    //             accNoValidationData[index1] = index1;
    //             count++;
    //         }
            
    //     }
    // }
    
   

}
