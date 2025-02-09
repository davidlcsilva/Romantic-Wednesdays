package romantic.wednesdays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
	
	public static Set<Pair> repeatedNumbers = new HashSet<Pair>();
	
	public static Set<Integer> participants = new HashSet<Integer>();
	
	public static int MAX_SHUFFLE_ATTEMPTS = 100;

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Enter all participants");
            System.out.println("2. Generate the participant pairs");
            System.out.println("3. Add participant");
            System.out.println("4. Remove participant");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            String input;
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1: // User enters numbers
                    System.out.print("\nEnter numbers separated by commas: ");
                    input = scanner.nextLine();

                    try {
                        for (String num : input.split(",")) {
                        	participants.add(Integer.parseInt(num));
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input! Please enter only numbers.");
                        continue;
                    }
                    break;

                case 2:
                    System.out.print(getPairings(new ArrayList<>(participants)));
                    break;
                
                case 3:
                	System.out.print("\nNumber of the participant: ");
                	input = scanner.nextLine();
                	try {
                		participants.add(Integer.parseInt(input));
                	}catch (NumberFormatException e) {
                        System.out.println("Invalid input! Please enter only numbers.");
                        continue;
                    }
                    break;
                case 4:
                	System.out.print("\nNumber of the participant: ");
                	input = scanner.nextLine();
                	try {
                		participants.remove(Integer.parseInt(input));
                	}catch (NumberFormatException e) {
                        System.out.println("Invalid input! Please enter only numbers.");
                        continue;
                    }
                    break;
                case 5:
                    System.out.println("Exiting program. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option! Please choose 1, 2, 3, 4 or 5.");
                    continue;
            }
        }

	}
	
	 public static String getPairings(List<Integer> participantsList) {
	        int attempts = 0;

	        while (attempts < MAX_SHUFFLE_ATTEMPTS) {
	            Set<Pair> newPairs = new HashSet<>();
	            StringBuilder buildPairs = new StringBuilder();
	            Collections.shuffle(participantsList);
	            boolean validPairing = true;

	            for (int i = 0; i < participantsList.size() - 1; i += 2) {
	                Pair pair = new Pair(participantsList.get(i), participantsList.get(i + 1));
	                if (repeatedNumbers.contains(pair)) {
	                    validPairing = false;
	                    break;
	                }
	                newPairs.add(pair);
	                buildPairs.append("\n").append(pair);
	            }

	            if (validPairing) {
	                if (participantsList.size() % 2 != 0) {
	                    buildPairs.append(" + Number ").append(participantsList.get(participantsList.size() - 1));
	                }
	                repeatedNumbers.addAll(newPairs);
	                return buildPairs.toString();
	            }

	            attempts++;
	        }

	        return "Could not find unique pairings after " + MAX_SHUFFLE_ATTEMPTS + " attempts.";
	    }

}
