package com.satvik.bookexchange.compRandom;

import java.util.*;

public class chessMoves {
    public static void main(String args[]){
        char[][] arr = new char[][]{
                {'.', 'R', '.'},
                {'.', '.', '.'},
                {'B', 'Q', '.'}
        };
        int r = arr.length, c = arr.length;
        List<int[]> diagonal = new ArrayList<>(),
                vertical = new ArrayList<>(),
                both = new ArrayList<>();
        // diagonal = B
        // vertical = R
        // both = Q
        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                String item = String.valueOf(arr[i][j]);
                if(item.equalsIgnoreCase("B")){
                    diagonal.add(new int[]{i, j});
                }else if (item.equalsIgnoreCase("R")){
                    vertical.add(new int[]{i, j});
                }else if (item.equalsIgnoreCase("Q")){
                    both.add(new int[]{i, j});
                }
            }
        }
        int sum = 0, ver = 0, diag = 0, bo = 0;
        for(int[] pos:diagonal) diag += getDiagonalPositions(pos[0], pos[1], r, c, arr);
        for(int[] pos:vertical) ver += getVerticalPositions(pos[0], pos[1], r, c, arr);
        for(int[] pos:both) bo += getBothPositions(pos[0], pos[1], r, c, arr);
        sum = ver+diag+bo;
        System.out.println("vertical: "+ver+"\ndiagonal: "+diag+"\nboth: "+bo);

        //PRINT/RETURN SUM
    }
    public static int getDiagonalPositions(int a, int b, int r, int c, char[][] arr){
        int i = a, j = b, moves = 0;
        // top right
        while(true){
            i--;
            j++;
            if(i>=0 && j<c && arr[i][j] == '.') moves++;
            else break;
        }

        // top left
        i = a;
        j = b;
        while(true){
            i--;
            j--;
            if(i>0 && j>=0 && arr[i][j] == '.') moves++;
            else break;
        }

        // bottom right
        i = a;
        j = b;
        while(true){
            i++;
            j++;
            if(i<r && j<c && arr[i][j] == '.') moves++;
            else break;
        }

        // bottom left
        i = a;
        j = b;
        while(true){
            i++;
            j--;
            if(i<r && j>=0 && arr[i][j] == '.') moves++;
            else break;
        }

        return moves;
    }

    public static int getVerticalPositions(int a, int b, int r, int c, char[][] arr){
        int moves = 0;
        // right
        for(int j=b+1;j<c;j++){
            if(arr[a][j] == '.') moves++;
            else break;
        }

        // left
        for(int j=b-1;j>=0;j--){
            if(arr[a][j] == '.') moves++;
            else break;
        }

        //up
        for(int i=a-1;i>=0;i--){
            if(arr[i][b] == '.') moves++;
            else break;
        }

        //down
        for(int i=a+1;i<r;i++){
            if(arr[i][b] == '.') moves++;
            else break;
        }
        return moves;
    }

    public static int getBothPositions(int a, int b, int r, int c, char[][] arr){
        int moves = getVerticalPositions(a, b, r, c, arr) + getDiagonalPositions(a, b, r, c, arr);
        return moves;
    }
}
