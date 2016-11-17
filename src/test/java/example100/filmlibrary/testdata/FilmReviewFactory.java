package example100.filmlibrary.testdata;

import com.google.common.collect.Lists;
import example100.filmlibrary.entity.FilmReview;

import java.util.List;

/**
 * Created on 17.11.2016.
 * Time 16:13.
 *
 * @author Volodymyr Portianko
 */
public class FilmReviewFactory {

    public static final ModelMatcher<FilmReview, String> FR_MATCHER = new ModelMatcher<>(FilmReview::toString);

    public static FilmReview getTerminatorReview1() {
        return new FilmReview(1, UserFactory.getUser1(), FilmFactory.getTerminatorFilm(), TERMINATOR_REVIEW_1, 10);
    }

    public static FilmReview getStarWarsReview1() {
        return new FilmReview(2, UserFactory.getUser1(), FilmFactory.getStarWarsFilm(), STAR_WARS_REVIEW_1, 5);
    }

    public static FilmReview getIndianaJonesReview1() {
        return new FilmReview(3, UserFactory.getUser1(), FilmFactory.getIndianaJonesFilm(), INDIANA_JONES_REVIEW_1, 0);
    }

    public static FilmReview getIndianaJonesReview2() {
        return new FilmReview(4, UserFactory.getUser2(), FilmFactory.getIndianaJonesFilm(), INDIANA_JONES_REVIEW_2, 7);
    }

    public static FilmReview getStarWarsReview2() {
        return new FilmReview(5, UserFactory.getUser2(), FilmFactory.getStarWarsFilm(), STAR_WARS_REVIEW_2, 3);
    }

    public static List<FilmReview> getAllReviewsOfUser1() {
        return Lists.newArrayList(getTerminatorReview1(), getStarWarsReview1(), getIndianaJonesReview1());
    }

    public static List<FilmReview> getAllReviewsOfStarWarsFilm() {
        return Lists.newArrayList(getStarWarsReview1(), getStarWarsReview2());
    }

    public static List<FilmReview> getAllReviewsOfTerminatorFilm() {
        return Lists.newArrayList(getTerminatorReview1());
    }

    public static FilmReview getNewTerminatorReview() {
        return new FilmReview(null, UserFactory.getUser2(), FilmFactory.getTerminatorFilm(), "new review", 6);
    }

    public static FilmReview getUpdatedIndianaJonesReview1() {
        FilmReview filmReview = getIndianaJonesReview1();
        filmReview.setRating(4);
        filmReview.setReview("updated review");
        return filmReview;
    }

    private static final String TERMINATOR_REVIEW_1 = "I am not a big fan of sequels,as most of them disappoint,but " +
            "T2 certainly does not.In fact,it's a rare case,at least in my opinion, of a sequel actually surpassing the " +
            "original film in terms of greatness.As in 99.9% of his films,Arnold Schwarzenegger is the good guy once " +
            "again,but you don't mind once you witness the incredible villain performance of Robert Patrick.This film " +
            "is nothing short of a beginning to end thrill ride.Let us not forget the talents of Linda Hamilton and " +
            "Edward Furlong,who gave great supporting efforts.Thumbs up!";

    private static final String STAR_WARS_REVIEW_1 = "Star Wars is a movie that has had great social impact, a fact that" +
            " has often gone unnoticed. A harbinger of a changing mood within the United States, Star Wars was one of the" +
            " few movies rated General that was released in 1977. Where movies had for a decade been depicting ever more" +
            " dark topics (Taxi Driver, The Exorcist) Star Wars was a lighthearted adventure. While some may decry the move" +
            " back to swashbuckling from social comment, I for one celebrate the fact that Star Wars made it possible for" +
            " families to go to movies together once again.";

    private static final String INDIANA_JONES_REVIEW_1 = "i understand its just a movie, but this movie is full of stupid" +
            " lies, deliberately made to show India and Hinduism in a very bad way. The depiction of Indian dishes such" +
            " as baby snakes, eyeball soup, beetles and chilled monkey brains are ridiculous in the country where majority" +
            " are vegetarians. in a dialogue where villain says Kali ma will take over Muslim Jews Christian god in the " +
            "world were made to look like Hindus are evil people. depiction of the goddess Kali as a representative of " +
            "the underworld and evil was met with much criticism, as she is almost exclusively depicted as a goddess of " +
            "change and empowerment (Shakti), meaning that while she does destroy, she almost always does so in order to " +
            "affect positive change. showing the things wrongly will sure offend everyone.";

    private static final String INDIANA_JONES_REVIEW_2 = "Temple of Doom may not be as good as Raiders, but it doesn't deserve" +
            " all this negative flak. The story is a little darker but that doesn't take anything away from the film. It makes" +
            " the situation that much more dire. John Williams' score infuses the sacrifice sequence with a sense of building" +
            " dread. The chanting, the heavy drums all building into a wild climax of heart burning and lava filled mayhem." +
            " The mine car chase is wild fun and Indy's bridge manuver is one hell of a climax. Still don't know why everyone's" +
            " so down on this movie.";

    private static final String STAR_WARS_REVIEW_2 = "Star wars made epic fantasy real. For a generation of people it " +
            "has defined what the cinema experience is meant to be. Today it is probable that pc games will offer a deeper" +
            " and more satisfying entertainment solution, but for pure visual and aural pleasure, mixed with basic emotional" +
            " manipulation, there has never and will never be a better example of cinema than when star wars appeared" +
            " over 25 years ago. When you think of star wars, you must remember what else was happening at the time. " +
            "In America, the war in Vietnam had been lost. In the U.K economic disaster was occurring(a 3 day working week," +
            " and the army collecting rubbish). It was almost like the two most technically advanced countries in the world " +
            "were going backwards. Star wars let everybody escape from that reality and reach for a future that was uncertain" +
            " but ultimately good.";
}
