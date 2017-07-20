package ru.yarikbur.game.player;

public class Stats {
	private static String Name = "NULL"; // Имя
	private static float Mana = 100f;  // Мана
	private static float Health = 200f; // Жизни
	private static float Force = 10f; // Сила
	private static float Stamina = 9f; // Выносливость
	private static float Adroitness = 11f; // Ловкость
	private static float Intelligence = 8f; // Интеллект
	private static float Experience = 6f; // Опыт
	private static float Luck = 9f; // Удача
	
	
	public String getName(){ return Name; }
	public float getMana(){ return Mana; }
	public float getHealth(){ return Health; }
	public float getForce(){ return Force; }
	public float getStamina(){ return Stamina; }
	public float getAdroitness(){ return Adroitness; }
	public float getIntelligence(){ return Intelligence; }
	public float getExperience(){ return Experience; }
	public float getLuck(){ return Luck; }
	
	
	public void setName(String Name){ Stats.Name = Name; }
	public void setMana(float Mana){ Stats.Mana = Mana; }
	public void setHealth(float Health){ Stats.Health = Health; }
	public void setForce(float Force){ Stats.Force = Force; }
	public void setStamina(float Stamina){ Stats.Stamina = Stamina; }
	public void setAdroitness(float Adroitness){ Stats.Adroitness = Adroitness; }
	public void setIntelligence(float Intelligence){ Stats.Intelligence = Intelligence; }
	public void setExperience(float Experience){ Stats.Experience = Experience; }
	public void setLuck(float Luck){ Stats.Luck = Luck; }
}
