package pit.rogue;

public enum EnemyTypes {
	Enemy1(15f, 10f, 2f, "Bat.png"),
	Enemy2(10f, 20f, 1f, "Enemy2.png"),
	Enemy3(7.5f, 50f, 3f, "walker.png");
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
