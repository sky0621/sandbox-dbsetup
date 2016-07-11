import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.Destination;
import com.ninja_squad.dbsetup.destination.DriverManagerDestination;
import com.ninja_squad.dbsetup.operation.Operation;

import static com.ninja_squad.dbsetup.Operations.*;

/**
 * Created by SS on 2016/07/11.
 */
public class Main {
    public static void main(String... args) {
        Destination dest = new DriverManagerDestination("jdbc:mysql://localhost/" + args[0], args[1], args[2]);
//        Operation DELETE_ALL = deleteAllFrom("mst_category");
        Operation DELETE_PARTIAL = sql("delete from mst_category where category_id = 3");
        Operation INSERT =
                insertInto("mst_category")
                        .columns("category_id", "category_name")
                        .values("3", "History")
                        .build();
        Operation ops = sequenceOf(DELETE_PARTIAL, INSERT);
        DbSetup dbSetup = new DbSetup(dest, ops);
        dbSetup.launch();
    }
}
