package comp3111.qsproject;

public class RecommendItem {
    String name;

    String bestYear;

    String bestRank;

    String recentYear;

    String recentRank;

    RecommendItem(QSItem item) {
        name = item.name;
        bestYear = item.year;
        bestRank = item.rank;
        recentYear = item.year;
        recentRank = item.rank;
    }

    void update(QSItem item) {
        assert (item.name.equals(name));
        /*
            Your Code Here.
            This function update the information from other QSItem.
            1. Update the best rank and the corresponding year.
            2. Update the most recent year and the corresponding rank.
         */
        int thisBestYear = Integer.parseInt(this.bestYear);
        int thisBestRank = Integer.parseInt(this.bestRank);
        int thisRecentYear = Integer.parseInt(this.recentYear);
        int thisRecentRank = Integer.parseInt(this.recentRank);
        int itemYear = Integer.parseInt(item.year);
        int itemRank = Integer.parseInt(item.rank);
        if (thisRecentYear < itemYear) {
            this.recentYear = item.year;
            this.recentRank = item.rank;
        }
        if (thisBestRank > itemRank) {
            this.bestRank = item.rank;
            this.bestYear = item.year;
        }

    }

    public String getName() { return name; }

    public String getBestYear() { return bestYear; }

    public String getBestRank() { return bestRank; }

    public String getRecentYear() { return recentYear; }

    public String getRecentRank() { return recentRank; }
}
