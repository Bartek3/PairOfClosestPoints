public class UI {
    public static void startMessage() {
        System.out.println("Witaj w programie Pair, który pomoze Ci znalezc pare najblizszych punktow w danym zbiorze!");
    }

    public static void writeRequirements(){
            System.out.println("Podaj kolejno: \n- Ilosc punktow, ktore chcesz wprowadzic \n- Punkty oddzielone spacja");
    }

    public static void writeSolution(Section solution){
        if (solution == null) System.out.println("Nalezy wprowadzic wiecej niz jeden punkt. Program zostanie teraz zamkniety.");
        else System.out.println("Najblizsza para punktow w podanym zbiorze to " + solution.toString() + ".\nSa one oddalone od siebie o " + Math.floor(solution.length() * 1000) / 1000 + " jednostek.");
    }
}
