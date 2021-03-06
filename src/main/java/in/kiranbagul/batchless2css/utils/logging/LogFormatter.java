package in.kiranbagul.batchless2css.utils.logging;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class LogFormatter extends Formatter{

    /* (non-Javadoc)
     * @see java.util.logging.Formatter#format(java.util.logging.LogRecord)
     */
    @Override
    public String format(LogRecord record) {
        StringBuilder sb = new StringBuilder();
        sb.append(record.getLevel()).append(':').append(record.getMessage()).append('\n');
        return sb.toString();
    }    
}