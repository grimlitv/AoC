package tv.grimli.y2015.d09;

public class CityDist {

  private int dist = 0;
  private String city = "";

  public CityDist(String city, int dist) {
    this.dist = dist;
    this.city = city;
  }

  public CityDist() {
  }

  public int getDist() {
    return dist;
  }

  public void setDist(int dist) {
    this.dist = dist;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

}

