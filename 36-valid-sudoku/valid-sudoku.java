class Solution {
    public boolean isValidSudoku(char[][] board) {
        Map <Integer,HashSet<Character>> row= new HashMap<>();
        Map <Integer,HashSet<Character>> col= new HashMap<>();
        Map <String,HashSet<Character>> sq= new HashMap<>();

        for(int r=0;r<9;r++)
        {
            for(int c=0;c<9;c++)
        {
            if (board[r][c]=='.')
            continue;

            String sk=r/3+","+c/3;
            if(row.computeIfAbsent(r, k -> new HashSet<>()).contains(board[r][c]) ||
            col.computeIfAbsent(c,k->new HashSet<>()).contains(board[r][c]) || 
            sq.computeIfAbsent(sk,k->new HashSet<>()).contains(board[r][c]))
            return false;
            else{
                row.get(r).add(board[r][c]);
                col.get(c).add(board[r][c]);
                sq.get(sk).add(board[r][c]);
            }
        }
        }

        return true;

    }
}