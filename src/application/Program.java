package application;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ChessMatch chessMatch = new ChessMatch();
        List<ChessPiece> captured = new ArrayList<>();
        while(!chessMatch.getCheckMate()){
            try{
                UI.clearScreen();
                UI.printMatch(chessMatch, captured);
                System.out.print("Source: ");
                ChessPosition source = UI.readChessPosition(scan);

                boolean[][] possibleMoves = chessMatch.possibleMoves(source);
                UI.clearScreen();
                UI.printBoard(chessMatch.getPieces(), possibleMoves);
                System.out.print("Target: ");

                ChessPosition target = UI.readChessPosition(scan);

                ChessPiece capturedPiece = chessMatch.performChessMove(source, target);

                if(capturedPiece != null){
                    captured.add(capturedPiece);
                }

                if(chessMatch.getPromoted() != null){
                    System.out.print("Enter piece for Promotion [ bishop, knight, rook, queen ]:");
                    String type = scan.nextLine().toLowerCase();
                    while (!type.equals("bishop") && !type.equals("knight") && !type.equals("rook") && !type.equals("queen")){
                        type = scan.nextLine().toLowerCase();
                    }
                    chessMatch.replacePromotedPiece(type);
                }

            }catch (ChessException | InputMismatchException e) {
                System.out.println(e.getMessage());
                scan.nextLine();
            }

        }

        UI.clearScreen();
        UI.printMatch(chessMatch, captured);

    }
}
