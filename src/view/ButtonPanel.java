package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.GameManager;
import model.Skill;

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
				manager.setSkill(Skill.DIGGER);
			}
		});
		exploder.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				manager.setSkill(Skill.EXPLODER);
			}
		});
		floater.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				manager.setSkill(Skill.FLOATER);
			}
		});
		miner.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				manager.setSkill(Skill.MINER);
			}
		});
		blocker.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				manager.setSkill(Skill.BLOCKER);
			}
		});
		climber.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				manager.setSkill(Skill.CLIMBER);
			}
		});
		builder.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				manager.setSkill(Skill.BUILDER);
			}
		});
		basher.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				manager.setSkill(Skill.BASHER);
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
