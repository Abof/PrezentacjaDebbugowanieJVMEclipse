package p1.elements;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SeriousLogic {
	private Collection<Word> words;
	private String suffix;
	
	public SeriousLogic(Collection<Word> words, String suffix) {
		super();
		this.words = words;
		this.suffix = suffix;
	}

	public void doItNow() {
		// Która opcja jest najlepsza do debuggowania poniższego streama?
		// 1) STEP OVER :/
		// 2) STEP INTO :/
		// 3) FILTORWANIE :)
		words.stream()
			.map(word -> {
				String newWord = word.getWord() + suffix;
				newWord = " \t{" + newWord + "} \t";
				newWord = newWord.trim();
				return newWord;})
			.forEach(System.out::println);
	
	}
}
