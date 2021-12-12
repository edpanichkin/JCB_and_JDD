package academy.kovalevskyi.javadeepdive.week0.day3;

import academy.kovalevskyi.javadeepdive.week0.day2.Csv;

public class JoinRequest extends AbstractRequest<Csv> {
    Csv on;
    String byValue;
        private JoinRequest(Csv from, Csv on, String byValue) {
            super(from);
            this.on = on;
            this.byValue = byValue;
        }
        protected Csv execute() throws RequestException {
          String[] newHeaders = new String[csv.headers().length + on.headers().length - 1];
          //HARDCODE BY = ID!
          System.arraycopy(csv.headers(), 0, newHeaders, 0, csv.headers().length);
          System.arraycopy(on.headers(), 1, newHeaders, csv.headers().length, on.headers().length-1);
          String[][] newValues = new String[csv.values().length][newHeaders.length];
          int count = 0;
          for (int i = 0; i < newValues.length; i++) {
            if(csv.values()[i][0].equals(on.values()[i][0])) {
              System.arraycopy(csv.values()[count], 0, newValues[count], 0, csv.values()[count].length);
              System.arraycopy(on.values()[count], 1, newValues[count], csv.values()[count].length, on.values()[count].length - 1);
              count++;
            }
          }
          return new Csv.Builder().
                  header(newHeaders).
                  values(newValues).build();
        }

        public static class Builder {
           Csv fromCsv;
           Csv onCsv;
           String byValue;

            public Builder from(Csv from) {
                this.fromCsv = from;
                return this;
            }

            public Builder on(Csv on) {
                this.onCsv = on;
                return this;
            }

            public Builder by(String by) {
                this.byValue = by;
                return this;
            }
            public JoinRequest build() {
                return new JoinRequest(fromCsv, onCsv, byValue);
            }
        }
}
