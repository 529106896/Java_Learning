package lab5_2;

import javax.swing.JFrame;

public class drawTest 
{
	public static void main(String[] args)
	{
		DrawPanel panel = new DrawPanel();
		JFrame app = new JFrame();
		
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		//设置窗口是否可以关闭
		app.add(panel);
		app.setTitle("这是一个画图程序");	//设置窗口标题
		app.setSize(800, 600);
		app.setVisible(true);
		//app.repaint();
	}
}
