package filters;

import twitter4j.Status;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class OrFilter implements Filter {
    private final Filter left;
    private final Filter right;

    public OrFilter(Filter left, Filter right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean matches(Status s) {
        return left.matches(s) || right.matches(s);
    }

    @Override
    public List<String> terms() {
        List<String> all_terms = new ArrayList<String>();
        all_terms.addAll((List< String>) left);
        all_terms.addAll((List<String>) right);
        return all_terms;
    }

    public String toString() {
        return "("+ left.toString() + " or " + right.toString() + ")";
    }
}
