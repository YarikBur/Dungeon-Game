package ru.yarikbur.game.player;

import java.math.BigDecimal;

public class Stats {
	private static long startTime;
	
	private static String Name = "NULL"; // Имя
	private static float ManaMax = 100;  // Мана Максимум
	private static float HealthMax = 200; // Жизни Максимум
	private static float Mana = ManaMax;  // Мана
	private static float Health = HealthMax; // Жизни
	private static float Force = 10; // Сила
	private static float Stamina = 9; // Выносливость
	private static float Adroitness = 11; // Ловкость
	private static float Intelligence = 8; // Интеллект
	private static int Experience = 0; // Опыт
	private static int ExperienceMax = newExperienceMax(); // Опыт Максимум
	private static float Luck = 9; // Удача
	private static float Wisdom = 6; // Мудрость
	private static float regenerationManaAdd = 0;
	private static float regenerationHealthAdd = 0;
	private static float regenerationMana=0;
	private static float regenerationHealth=0;
	private static int Level = 1; // Уроыень персонажа
	private static int Points = 0; // Очки усиления
	
	private static float Int = Intelligence;
	private static float St = Stamina;
	private static float time = 0;
	
	public String getName(){ return Name; }
	public float getMana(){ return Mana; }
	public float getHealth(){ return Health; }
	public float getManaMax(){ return ManaMax; }
	public float getHealthMax(){ return HealthMax; }
	public float getForce(){ return Force; }
	public float getStamina(){ return Stamina; }
	public float getAdroitness(){ return Adroitness; }
	public float getIntelligence(){ return Intelligence; }
	public int getExperience(){ return Experience; }
	public int getExperienceMax(){ return ExperienceMax; }
	public float getLuck(){ return Luck; }
	public float getWisdom(){ return Wisdom; }
	public float getRegenerationMana(){ return regenerationMana; }
	public float getRegenerationHealth(){ return regenerationHealth; }
	public int getLevel(){ return Level; }
	public int getPoints(){ return Points; }
	
	
	public void setName(String Name){ Stats.Name = Name; }
	public void setMana(float Mana){ Stats.Mana = Mana; }
	public void setHealth(float Health){ Stats.Health = Health; }
	public void setManaMax(float Mana){ Stats.ManaMax = Mana; }
	public void setHealthMax(float Health){ Stats.HealthMax = Health; }
	public void setForce(float Force){ Stats.Force = Force; }
	public void setStamina(float Stamina){ Stats.Stamina = Stamina; }
	public void setAdroitness(float Adroitness){ Stats.Adroitness = Adroitness; }
	public void setIntelligence(float Intelligence){ Stats.Intelligence = Intelligence; }
	public void setExperience(int Experience){ Stats.Experience = Experience; }
	public void setExperienceMax(int ExperienceMax){ Stats.ExperienceMax = ExperienceMax; }
	public void setLuck(float Luck){ Stats.Luck = Luck; }
	public void setWisdom(float Wisdom){ Stats.Wisdom = Wisdom; }
	public void setStartTime(long startTime){ Stats.startTime = startTime; }
	public void setLevel(int level){ Stats.Level = level; }
	public void setPoints(int points){ Stats.Points = points; }
	
	public void regenerationStats(){
		regenerationMana = (Wisdom/100*5 + ManaMax/100*2 + regenerationManaAdd);
		regenerationHealth = (Stamina/100*5 + HealthMax/100*1 + regenerationHealthAdd);
		regenerationMana = BigDecimal.valueOf(regenerationMana).setScale(1,BigDecimal.ROUND_HALF_DOWN).floatValue();
		regenerationHealth = BigDecimal.valueOf(regenerationHealth).setScale(1,BigDecimal.ROUND_HALF_DOWN).floatValue();
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
		

		ManaMax = BigDecimal.valueOf(ManaMax).setScale(1,BigDecimal.ROUND_HALF_DOWN).floatValue();
		HealthMax = BigDecimal.valueOf(HealthMax).setScale(1,BigDecimal.ROUND_HALF_DOWN).floatValue();
		Mana = BigDecimal.valueOf(Mana).setScale(1,BigDecimal.ROUND_HALF_DOWN).floatValue();
		Health = BigDecimal.valueOf(Health).setScale(1,BigDecimal.ROUND_HALF_DOWN).floatValue();
	}
	
	private static int newExperienceMax(){
		float newLevel = 100f+((Force+Stamina+Adroitness+Intelligence+Luck+Wisdom+Points)/2f)+Stamina+Intelligence+Force+Wisdom+(Level*2f)+(((ManaMax*1.5f)+HealthMax)/2f);
		return BigDecimal.valueOf(newLevel).setScale(0,BigDecimal.ROUND_HALF_DOWN).intValue();
	}
	
	private void levelUp(){
		float newManaMax = ManaMax + ManaMax/100*6;
		ManaMax = BigDecimal.valueOf(newManaMax).setScale(1,BigDecimal.ROUND_HALF_DOWN).floatValue();
		float newHealthMax = HealthMax + HealthMax/100*4;
		HealthMax = BigDecimal.valueOf(newHealthMax).setScale(1,BigDecimal.ROUND_HALF_DOWN).floatValue();
		Points+=5;
		Level++;
		int remainder = -ExperienceMax + Experience;
		if(remainder<0) Experience = 0;
		else Experience = remainder;
		ExperienceMax = newExperienceMax();
	}
	
	public void updateLevel(){
		if(Experience>=ExperienceMax) levelUp();
	}
}
