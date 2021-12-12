package academy.kovalevskyi.javadeepdive.week0.day3;

import academy.kovalevskyi.javadeepdive.week0.day2.Csv;

public class DeleteRequest  extends AbstractRequest<Csv> {
    Selector where;

    private DeleteRequest(Csv target, Selector whereSelector) {
        super(target);
        this.where = whereSelector;
    }
    protected Csv execute() throws RequestException {
        if (this.csv == null) {
            throw new RequestException();
        }
        int indexToDelete = findIndexInRows(csv, where).get(0);
        String[][] newValue = new String[csv.values().length -1][];
        int counter = 0;
        for (int i = 0; i < csv.values().length; i++) {
            if(i == indexToDelete) {
                continue;
            }
            newValue[counter++] = csv.values()[i];
        }
        return new Csv.Builder().header(csv.headers()).values(newValue).build();
    }

    public static class Builder {
        Csv csv;
        Selector where;

        public Builder where(Selector selector) {
            this.where = selector;
            return this;
        }

        public Builder from(Csv csv) {
            this.csv = csv;
            return this;
        }

        public DeleteRequest build() {
            return new DeleteRequest(csv, where);
        }
    }

    public void printSelector(Selector selector) {
        System.out.printf("FieldName %s / Value %s \n", selector.fieldName(), selector.value());
    }
}