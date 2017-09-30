package pit.rogue;

public enum EnemyTypes {
	Enemy1(15f, 10f, 2f, "Bat.png"),
	Enemy2(10f, 10f, 1f, "Enemy2.png"),
	Enemy3(10f, 15f, 2f, "");
	
	float speed;
	float health;
	float strength;
	String textureName;
	
	EnemyTypes(float speed, float health, float strength, String textureName) {
		this.speed = speed;
		this.health = health;
		this.strength = strength;
		this.textureName = textureName;
	}
	
}
