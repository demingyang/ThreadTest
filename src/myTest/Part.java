package myTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by YangDeming on 2018/3/2.
 */
class Filter extends Part {}
class FuelFilter extends Filter {
    public static class Factory implements myTest.Factory<FuelFilter> {
        public FuelFilter create() {
            return new FuelFilter();
        }
    }
}
class AirFilter extends Filter {
    public static class Factory implements myTest.Factory<AirFilter> {
        public AirFilter create() {
            return new AirFilter();
        }
    }
}

class Belt extends Part {}
class FanBelt extends Belt {
    public static class Factory implements myTest.Factory<FanBelt> {
        public FanBelt create() {
            return new FanBelt();
        }
    }
}
class GeneratorBelt extends Belt {
    public static class Factory implements myTest.Factory<GeneratorBelt> {
        public GeneratorBelt create() {
            return new GeneratorBelt();
        }
    }
}
public class Part {
    static List<Factory<? extends Part>> partFactories = new ArrayList<Factory<? extends Part>>();

    static {
        partFactories.add(new FuelFilter.Factory());
        partFactories.add(new AirFilter.Factory());
        partFactories.add(new FanBelt.Factory());
//        partFactories.add(new PowerSteeringBelt.Factory());
    }

    private static Random rand = new Random(47);

    public static Part createRandom() {
        int n = rand.nextInt(partFactories.size());
        return partFactories.get(n).create();
    }

    public String toString() {
        return getClass().getSimpleName();
    }


}
