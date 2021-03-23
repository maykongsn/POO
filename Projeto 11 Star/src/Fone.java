public class Fone {
	private String name;
	private String number;

	public Fone(String name, String number) {
		if(!validateNumber(number)) {
			throw new RuntimeException("fail: número inválido");
		}
		this.name = name;
		this.number = number;
	}

	public String getName() {
		return this.name;
	}

	public String getNumber() {
		return this.number;
	}

	public static boolean validateNumber(String number) {
		String solve = "0123456789()-";
		for(int i = 0; i < number.length(); i++) {
			if(solve.indexOf(number.charAt(i)) == -1) {
				return false;
			}
		}
		return true;
	}

	public String toString() {
		return this.name + ":" + this.number;
	}
}
