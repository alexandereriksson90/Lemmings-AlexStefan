package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.GameManager;

public class ButtonPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	GameManager manager;

	public ButtonPanel(GameManager manager)
	{
		this.manager = manager;
		createPanel();
	}

	private void createPanel()
	{
		JButton digger = new JButton("Digger");
		JButton exploder = new JButton("Exploder");
		JButton floater = new JButton("Floater");
		JButton miner = new JButton("Miner");
		JButton blocker = new JButton("Blocker");
		JButton climber = new JButton("Climber");
		JButton builder = new JButton("Builder");
		JButton basher = new JButton("Basher");
		JButton paus = new JButton("Pause");
		JButton end = new JButton("End");

		digger.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				manager.setBehaviour("Digger");
			}
		});
		exploder.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				manager.setBehaviour("Exploder");
			}
		});	
		floater.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				manager.setBehaviour("Floater");
			}
		});
		miner.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				manager.setBehaviour("Miner");
			}
		});
		blocker.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				manager.setBehaviour("Blocker");
			}
		});
		climber.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				manager.setBehaviour("Climber");
			}
		});
		builder.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				manager.setBehaviour("Builder");
			}
		});
		basher.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				manager.setBehaviour("Basher");
			}
		});
		paus.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				manager.pause();
			}
		});
		end.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{

			}
		});
		
		add(miner);
		add(digger);
		add(exploder);
		add(basher);
		add(builder);
		add(climber);
		add(floater);
		add(blocker);
		add(paus);
		add(end);

	}

}
