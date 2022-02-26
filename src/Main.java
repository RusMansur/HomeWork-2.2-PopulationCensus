import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        //Количество персон младше 18 лет
        Stream<Person> streamMinors = persons.stream();
        long minors = streamMinors
                .filter(age -> age.getAge() < 18)
                .count();
        System.out.println(minors);

        //Список призывников
        Stream<Person> streamRecruits = persons.stream();
        List<String> recruits;
        recruits = persons.stream()
                .filter(age -> age.getAge() > 18)
                .filter(age -> age.getAge() < 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
//        System.out.println(recruits); //Можно вывести в консоль

        //Список персон с высшим образованием
        Stream<Person> streamHigherEducation = persons.stream();
        List<Person> personsHigherEducation;
        personsHigherEducation = persons.stream()
                .filter(education -> education.getEducation().equals(Education.HIGHER))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toCollection(ArrayList::new));
//        System.out.println(personsHigherEducation);//Можно вывести в консоль
    }
}
