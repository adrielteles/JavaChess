package application;

import chess.ChessPiece;
import chess.ChessPosition;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UI {

    public static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static ChessPosition readChessPosition(Scanner scan){
        try{
            String move = scan.nextLine();
            char column = move.charAt(0);
            int row = Integer.parseInt(move.substring(1));

            return new ChessPosition(column,row);
        }
        catch (RuntimeException e){
            throw new InputMismatchException("Error reading ChessPosition. valid values are from a1 o h8.");
        }

    }
    public static void printBoard(ChessPiece[][] pieces){
        for(int i=0; i<pieces.length; i++){
            System.out.print((8-i) + " ");
            for(int j=0; j< pieces.length; j++){
                printPiece(pieces[i][j]);
            }
            System.out.println();
        }
        System.out.println("  A  B  C  D  E  F  G  H");
    }
    private static void printPiece(ChessPiece piece){
        if(piece == null){
            System.out.print("–");
        }else{
            System.out.print(piece);
        }
        System.out.print("  ");
    }
}
