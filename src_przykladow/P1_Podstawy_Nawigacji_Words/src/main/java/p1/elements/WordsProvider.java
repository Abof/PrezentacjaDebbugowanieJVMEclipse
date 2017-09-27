package p1.elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordsProvider {

	private int x,y,z;
	private List<Word> wordList;
	
	public WordsProvider(int x, int y, int z) {
		super();
		// bardzo
		// nudny
		// konstruktor
		// ...jak prawie każdy
		this.x = x;
		this.y = y;
		this.z = z;
		wordList = new ArrayList<>();
	}

	public List<Word> provide() {
		System.out.print("Pobieram słówka! Przy okazji je policzę!.. ");
		Arrays.stream(FIFTEEN_COMMON_WORDS)
		.forEach(commonWord -> {
			wordList.add(new Word(commonWord));
			System.out.print(wordList.size() + " ");
		});
		
		return wordList;
	}
	
	public static final String[] FIFTEEN_COMMON_WORDS = 
			new String[] {"Kot","Pies","Osa","Nosa","Urbanek",
					"Przykład",	"Bambo","Lokomotywa","Kiełbasa","Laptop",
					"Yerba","Kawa","Tablica","Automobil","Kubek"};
	
}
