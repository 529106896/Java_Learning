package lab15;
// Fig. 27.13: TicTacToeServer.java
// Server side of client/server Tic-Tac-Toe program.
import java.awt.BorderLayout;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.util.Formatter;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class TicTacToeServer extends JFrame 
{
   private String[] board = new String[ 9 ]; // tic-tac-toe board
   private JTextArea outputArea; // for outputting moves
   private Player[] players; // array of Players
   private ServerSocket server; // server socket to connect with clients
   private int currentPlayer; // keeps track of player with current move
   private final static int PLAYER_X = 0; // constant for first player
   private final static int PLAYER_O = 1; // constant for second player
   private final static String[] MARKS = { "X", "O" }; // array of marks
   private ExecutorService runGame; // will run players
   private Lock gameLock; // to lock game for synchronization
   private Condition otherPlayerConnected; // to wait for other player
   private Condition otherPlayerTurn; // to wait for other player's turn
   private String winner = "";
   private boolean isGameOver = false;
   //private TicTacToeClient client = new TicTacToeClient();

   // set up tic-tac-toe server and GUI that displays messages
   public TicTacToeServer()
   {
      super( "Tic-Tac-Toe Server" ); //窗口标题

      //为每个玩家添加一个线程池
      runGame = Executors.newFixedThreadPool( 2 );
      gameLock = new ReentrantLock(); // create lock for game

      // condition variable for both players being connected
      //这个condition用在建立连接阶段
      otherPlayerConnected = gameLock.newCondition();

      // condition variable for the other player's turn
      //这个condition用在正式运行阶段
      otherPlayerTurn = gameLock.newCondition();      

      for ( int i = 0; i < 9; i++ )
         board[ i ] = new String( "" ); //初始化棋盘
      
      //玩家数量有两个
      //Player是关键部分代码，是独立运行的线程，和client打交道
      players = new Player[ 2 ]; // create array of players
      
      //默认第一个玩家为X
      currentPlayer = PLAYER_X; // set current player to first player
 
      try
      {
    	  //用来和客户端通信的socket
    	  //利用指定的 backlog 创建服务器套接字并将其绑定到指定的本地端口号
         server = new ServerSocket( 12345, 2 ); // set up ServerSocket
      } // end try
      catch ( IOException ioException ) 
      {
         ioException.printStackTrace();
         System.exit( 1 );
      } // end catch

      //服务端是一个文本区域
      outputArea = new JTextArea(); // create JTextArea for output
      
      //把JTextArea添加到JFrame容器中
      add( outputArea, BorderLayout.CENTER );
      outputArea.setText( "Server awaiting connections\n" );
      outputArea.setEditable(false);

      setSize( 300, 300 ); // set size of window
      setVisible( true ); // show window
   } // end TicTacToeServer constructor

   // wait for two connections so game can be played
   //ServerTest里先调用构造函数，然后调用execute
   public void execute()
   {
      // wait for each client to connect
	   //players.length = 2
      for ( int i = 0; i < players.length; i++ ) 
      {
         try // wait for connection, create Player, start runnable
         {
        	//server.accpet，和客户端建立连接，返回一个新的套接字
            players[ i ] = new Player( server.accept(), i );
            runGame.execute( players[ i ] ); // execute player runnable
         } // end try
         catch ( IOException ioException ) 
         {
            ioException.printStackTrace();
            System.exit( 1 );
         } // end catch
      } // end for

      gameLock.lock(); // lock game to signal player X's thread

      try
      {
         players[ PLAYER_X ].setSuspended( false ); // resume player X
         otherPlayerConnected.signal(); // wake up player X's thread
      } // end try
      finally
      {
         gameLock.unlock(); // unlock game after signalling player X
      }
   }
   
   
   private void displayMessage( final String messageToDisplay )
   {
      SwingUtilities.invokeLater(
         new Runnable() 
         {
            public void run() // updates outputArea
            {
               outputArea.append( messageToDisplay ); // add message
            }
         }
      );
   }

   // determine if move is valid
   public boolean validateAndMove( int location, int player )
   {
      // while not current player, must wait for turn
      while ( player != currentPlayer ) 
      {
         gameLock.lock(); // lock game to wait for other player to go

         try 
         {
            otherPlayerTurn.await(); // wait for player's turn
         } // end try
         catch ( InterruptedException exception )
         {
            exception.printStackTrace();
         } // end catch
         finally
         {
            gameLock.unlock(); // unlock game after waiting
         } // end finally
      } // end while

      // if location not occupied, make move
      if ( !isOccupied( location ) )
      {
         board[ location ] = MARKS[ currentPlayer ]; // set move on board
         
         //如果现在是0,（0+1）%2 = 1，就切换到了1
         //如果现在是1，（1+1）%2 = 0，就切换到了0
         currentPlayer = ( currentPlayer + 1 ) % 2; // change player

         // let new current player know that move occurred
         players[ currentPlayer ].otherPlayerMoved( location );

         gameLock.lock(); // lock game to signal other player to go

         try 
         {
            otherPlayerTurn.signal(); // signal other player to continue
         } // end try
         finally
         {
            gameLock.unlock(); // unlock game after signaling
         } // end finally

         return true; // notify player that move was valid
      } // end if
      else // move was not valid
         return false; // notify player that move was invalid
   } // end method validateAndMove

   // determine whether location is occupied
   public boolean isOccupied( int location )
   {
      if ( board[ location ].equals( MARKS[ PLAYER_X ] ) || 
         board [ location ].equals( MARKS[ PLAYER_O ] ) )
         return true; // location is occupied
      else
         return false; // location is not occupied
   } // end method isOccupied

   // place code in this method to determine whether game over 
   public boolean isGameOver()
   {
	   return
			   (!board[0].equals("") && board[0].equals(board[1]) && board[0].equals(board[2]))
	              ||(!board[3].equals("") && board[3].equals(board[4]) && board[3].equals(board[5]))
	              ||(!board[6].equals("") && board[6].equals(board[7]) && board[6].equals(board[8]))
	              ||(!board[0].equals("") && board[0].equals(board[3]) && board[0].equals(board[6]))
	              ||(!board[1].equals("") && board[1].equals(board[4]) && board[1].equals(board[7]))
	              ||(!board[2].equals("") && board[2].equals(board[5]) && board[2].equals(board[8]))
	              ||(!board[0].equals("") && board[0].equals(board[4]) && board[0].equals(board[8]))
	              ||(!board[2].equals("") && board[2].equals(board[4]) && board[2].equals(board[6]));
      //return false; // this is left as an exercise
   } // end method isGameOver
   
   public boolean isTie()
   {
	   for(int i = 0; i < board.length; i++)
	   {
		   if(board[i].equals(""))
			   return false;
	   }
	   return true;
   }

   // private inner class Player manages each Player as a runnable
   //私有内部类把每个玩家作为一个单独的线程来管理
   private class Player implements Runnable 
   {
      private Socket connection; // connection to client
      
      //用来从客户端向服务端发送数据
      private Scanner input; // input from client
      
      //用来从服务端向客户端发送数据
      private Formatter output; // output to client
      
      //玩家序号
      private int playerNumber; // tracks which player this is
      
      //玩家所用符号
      private String mark; // mark for this player
      
      
      private boolean suspended = true; // whether thread is suspended

      //建立对应玩家的线程
      public Player( Socket socket, int number )
      {
    	  //玩家对应的序号，X是0，O是1
         playerNumber = number; // store this player's number
         
         //玩家对应的标记，X和O
         mark = MARKS[ playerNumber ]; // specify player's mark
         
         //和服务器连接的套接字
         connection = socket; // store socket for client
         
         try // obtain streams from Socket
         {
        	 //input是在服务端上显示的东西
            input = new Scanner( connection.getInputStream() );
            
            //output是在客户端上显示的东西
            //Formatter的参数表示要输出的目标位置，格式化的字符串将输出到目标位置
            output = new Formatter( connection.getOutputStream() );
         } // end try
         catch ( IOException ioException ) 
         {
            ioException.printStackTrace();
            System.exit( 1 );
         } // end catch
      } // end Player constructor

      // send message that other player moved
      public void otherPlayerMoved( int location )
      {
         output.format( "Opponent moved\n" );
         
         //这个location是用来让客户端识别位置的
         output.format( "%d\n", location ); // send location of move
         
         if(isGameOver())
        	 output.format("Defeat\n");
         else if(isTie())
        	 output.format("Tie\n");
         
         output.flush(); // flush output
      } // end method otherPlayerMoved

      // control thread's execution
      public void run()
      {
         // send client its mark (X or O), process messages from client
         try 
         {
            displayMessage( "Player " + mark + " connected\n" );
            
            ///这里是第一次向客户端发送信息，用来让客户端识别是哪个玩家
            output.format( "%s\n", mark ); // send player's mark
            output.flush(); // flush output

            // if player X, wait for another player to arrive
            if ( playerNumber == PLAYER_X ) 
            {
               output.format( "%s\n%s", "Player X connected",
                  "Waiting for another player\n" );
               output.flush(); // flush output

               gameLock.lock(); // lock game to  wait for second player

               try 
               {
                  while( suspended )
                  {
                	  //在O也连接上之后才会signal
                     otherPlayerConnected.await(); // wait for player O
                  } // end while
               } // end try 
               catch ( InterruptedException exception ) 
               {
                  exception.printStackTrace();
               } // end catch
               finally
               {
                  gameLock.unlock(); // unlock game after second player
               } // end finally

               // send message that other player connected
               output.format( "Other player connected. Your move.\n" );
               output.flush(); // flush output
            } // end if
            else
            {
               output.format( "Player O connected, please wait\n" );
               output.flush(); // flush output
            } // end else

            // while game not over
            while ( !isGameOver() && !isTie())
            {
               int location = 0; // initialize move location

               if ( input.hasNext() )
                  location = input.nextInt(); // get move location

               // check for valid move
               if ( validateAndMove( location, playerNumber ) ) 
               {
                  displayMessage( "\nlocation: " + location );
                  output.format( "Valid move.\n" ); // notify client
                  if(isGameOver())
                	  output.format("Victory\n");
                  else if(isTie())
                	  output.format("Tie\n");
                  output.flush(); // flush output
               } // end if
               else // move was invalid
               {
                  output.format( "Invalid move, try again\n" );
                  output.flush(); // flush output
               } // end else
               
            } // end while
         } // end try
         finally
         {
        	 if(isGameOver())
        		 displayMessage("\nWe have a Winner!");
        	 else if(isTie())
        		 displayMessage("\nTie! No winner or loser!");
            try
            {
               connection.close(); // close connection to client
            } // end try
            catch ( IOException ioException ) 
            {
               ioException.printStackTrace();
               System.exit( 1 );
            } // end catch
         } // end finally
      } // end method run

      // set whether or not thread is suspended
      public void setSuspended( boolean status )
      {
         suspended = status; // set value of suspended
      } // end method setSuspended
   } // end class Player
} // end class TicTacToeServer

/**************************************************************************
 * (C) Copyright 1992-2012 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/
