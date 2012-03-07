package gol;

import java.util.LinkedList;

public class Gol {

    private int maxX;
    private int maxY;
    // alue jota kaytetaan
    Cell[][] area;
    // jono jonne laitetaan muuttuneet solut, myohemin talletetaan ne taulukkoon
    private LinkedList<Cell> changeQueue;
    // jono johon laitetaan aina tarkistettavaksi asetetut solut
    private LinkedList<Cell> checkQueue;
    // kertoo onko firstRun suoritettu kerran alussa
    private boolean first;

    // luo tyhja alue taulukkoon
    public void initArea(int maxX, int maxY) {
        first = false;
        checkQueue = new LinkedList<Cell>();
        changeQueue = new LinkedList<Cell>();
        this.maxX = maxX - 1;
        this.maxY = maxY - 1;
        area = new Cell[maxX][maxY];
        for (int x = 0; x < maxX; x++) {
            for (int y = 0; y < maxY; y++) {
                area[x][y] = new Cell(x, y);
            }
        }
    }

    // tarkistaa naapureiden perusteella mita tehdaan
    private void makeChecks(int x, int y) {
        // laske ensin naapureiden maara
        int nCount = 0;
        Cell[] n = getNeighbours(x, y);
        for (Cell cell : n) {
            if (cell.isActive()) {
                nCount++;
            }
        }

        // jos 3, syntyy uusi. Laita solu muuttuneiden jonoon
        if (nCount == 3 && !area[x][y].isActive()) {
            Cell cell = new Cell(x, y);
            cell.setActive(true);
            changeQueue.add(cell);
	//ja sen naapurit tarkisjonoon
            for (Cell c : n) {
                if (!checkQueue.contains(c)) {
                    checkQueue.add(c);
                }
            }
        }

        // jos n enemman kuin 3, solu kuolee. Laita muuttuneiden jonoon
        if (nCount > 3 && area[x][y].isActive()) {
            Cell cell = new Cell(x, y);
            cell.setActive(false);
            changeQueue.add(cell);
	//ja sen naapurit tarkisjonoon
            for (Cell c : n) {
                if (!checkQueue.contains(c)) {
                    checkQueue.add(c);
                }
            }
        }

        // jos n vahemman kuin 2, solu kuolee sukupuuttoon. Laita solu muuttuneiden jonoon
        if (nCount < 2 && area[x][y].isActive()) {
            Cell cell = new Cell(x, y);
            cell.setActive(false);
            changeQueue.add(cell);
	//ja sen naapurit tarkistusjonoon
            for (Cell c : n) {
                if (!checkQueue.contains(c)) {
                    checkQueue.add(c);
                }
            }
        }
    }

    // ensimmainen generaatio suoritetaan talla, luo pohjan jonoille koko alueen perusteella
    private void firstRun() {
        for (int x = 0; x < maxX; x++) {
            for (int y = 0; y < maxY; y++) {
                makeChecks(x, y);
            }
        }
        first = true;
    }

    // tallenna muuttuneet solut taulukkoon
    private void flushNew() {
        while (changeQueue.size() > 0) {
            Cell cell = changeQueue.removeFirst();

            area[cell.getX()][cell.getY()] = cell;
        }
    }

    // aja seuraava generaatio
    public void generationX() {
        // tarkistus, onko ajettu forstRun
        if (first) {
            // otetaan talteen tarkistusjonoon talletettujen maaara
            int count = checkQueue.size();

            // aja silmukkaa edellisella kierroksella tarkistusjonoon laitettujen verran
            for (int i = 0; i < count; i++) {
                // poimi jonosta tarkistettava solu
                Cell cell = checkQueue.removeFirst();
		//aja sille tarkistukset
		makeChecks(cell.getX(), cell.getY());
                }
            }
        } else // jos firstRun ei ajettu, aja ensimmaisena generaationa
        {
            firstRun();
        }
        // talleta muuttuneet taulukkoon
        flushNew();
    }

    // tulosta alue
    public void printArea() {
        for (int y = 0; y < maxY + 1; y++) {
            for (int x = 0; x < maxX + 1; x++) {
                if (area[x][y].isActive()) {
                    System.out.print('x');
                } else {
                    System.out.print('.');
                }
            }
            System.out.println("");
        }
    }

    public void setCell(boolean active, int x, int y) {
        area[x][y].setActive(true);
    }

    public Cell[] getNeighbours(int x, int y) {
        // alusta tyhja solutaulukko
        Cell[] n = new Cell[8];

        // lasketaan ensin eri ilmansuuntien naapureiden arvot
        // alue on kiinni vastakkaisista reunoista, siis toroid=rinkeli
        int upper;
        if (y == 0) {
            upper = maxY;
        } else {
            upper = y - 1;
        }
        int lower;
        if (y == maxY) {
            lower = 0;
        } else {
            lower = y + 1;
        }
        int left;
        if (x == 0) {
            left = maxX;
        } else {
            left = x - 1;
        }
        int right;
        if (x == maxX) {
            right = 0;
        } else {
            right = x + 1;
        }

        // keraa palautettavaan taulukkoon naapurisolut
        n[0] = area[left][upper];
        n[1] = area[x][upper];
        n[2] = area[right][upper];
        n[3] = area[left][y];
        n[4] = area[right][y];
        n[5] = area[left][lower];
        n[6] = area[x][lower];
        n[7] = area[right][lower];
        return n;
    }

    public static void main(String[] args) {
        // TODO code application logic here
    }
}
