package ru.yarikbur.game.player;

public class Stats {
	private static long startTime;
	
	private static String Name = "NULL"; // Имя
	private static int ManaMax = 100;  // Мана Максимум
	private static int HealthMax = 200; // Жизни Максимум
	private static int Mana = ManaMax;  // Мана
	private static int Health = HealthMax; // Жизни
	private static int Force = 10; // Сила
	private static int Stamina = 9; // Выносливость
	private static int Adroitness = 11; // Ловкость
	private static int Intelligence = 8; // Интеллект
	private static int Experience = 0; // Опыт
	private static int Luck = 9; // Удача
	private static int Wisdom = 6; // Мудрость
	private static int regenerationManaAdd = 0;
	private static int regenerationHealthAdd = 0;
	private static double regenerationMana=0;
	private static double regenerationHealth=0;
	
	private static int Int = Intelligence;
	private static int St = Stamina;
	private static float time = 0;
	
	public String getName(){ return Name; }
	public int getMana(){ return Mana; }
	public int getHealth(){ return Health; }
	public int getManaMax(){ return ManaMax; }
	public int getHealthMax(){ return HealthMax; }
	public int getForce(){ return Force; }
	public int getStamina(){ return Stamina; }
	public int getAdroitness(){ return Adroitness; }
	public int getIntelligence(){ return Intelligence; }
	public int getExperience(){ return Experience; }
	public int getLuck(){ return Luck; }
	public int getWisdom(){ return Wisdom; }
	public double getRegenerationMana(){ return regenerationMana; }
	public double getRegenerationHealth(){ return regenerationHealth; }
	
	
	public void setName(String Name){ Stats.Name = Name; }
	public void setMana(int Mana){ Stats.Mana = Mana; }
	public void setHealth(int Health){ Stats.Health = Health; }
	public void setManaMax(int Mana){ Stats.ManaMax = Mana; }
	public void setHealthMax(int Health){ Stats.HealthMax = Health; }
	public void setForce(int Force){ Stats.Force = Force; }
	public void setStamina(int Stamina){ Stats.Stamina = Stamina; }
	public void setAdroitness(int Adroitness){ Stats.Adroitness = Adroitness; }
	public void setIntelligence(int Intelligence){ Stats.Intelligence = Intelligence; }
	public void setExperience(int Experience){ Stats.Experience = Experience; }
	public void setLuck(int Luck){ Stats.Luck = Luck; }
	public void setWisdom(int Wisdom){ Stats.Wisdom = Wisdom; }
	public void setStartTime(long startTime){ Stats.startTime = startTime; }
	
	public void regenerationStats(){
		regenerationMana = (Wisdom/100*5 + ManaMax/100*2 + regenerationManaAdd);
		regenerationHealth = (Stamina/100*5 + HealthMax/100*1 + regenerationHealthAdd);
		if(time!=(System.currentTimeMillis() - startTime) / 1000){
			time++;
			
			if(Mana!=ManaMax) {
				Mana += regenerationMana;
				if(Mana>ManaMax) Mana=ManaMax;
			}
			if(Health!=HealthMax) {
				Health += regenerationHealth;
				if(Health>HealthMax) Health=HealthMax;
			}
		}
	}
	
	public void updateStats(){
		if(Intelligence!=Int){
			ManaMax += ManaMax/100*4;
			Int++;
		}
		if(Stamina!=St){
			HealthMax += HealthMax/100*3;
			St++;
		}
	}
}
