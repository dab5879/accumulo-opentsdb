package org.hbase.async;

import java.util.Map.Entry;

import org.apache.accumulo.core.data.Key;
import org.apache.accumulo.core.data.Value;
import org.apache.accumulo.core.util.TextUtil;

public class KeyValue {
  
  private final byte[] key;
  private final byte[] family;
  private final byte[] qualifier;
  private final long timestamp;
  private final byte[] value;

  public KeyValue(byte[] key, byte[] family, byte[] qualifier, long timestamp, byte[] value) {
    this.key = key;
    this.family = family;
    this.qualifier = qualifier;
    this.timestamp = timestamp;
    this.value = value;
  }

  public KeyValue(Entry<Key,Value> next) {
    this(TextUtil.getBytes(next.getKey().getRow()),
        TextUtil.getBytes(next.getKey().getColumnFamily()),
        TextUtil.getBytes(next.getKey().getColumnQualifier()),
        next.getKey().getTimestamp(),
        next.getValue().get());
  }
  
  public KeyValue(byte[] key, byte[] family, byte[] qualifier, byte[] value) {
    this(key, family, qualifier, 0l, value);
  }

  public byte[] key() {
    return key;
  }

  public byte[] family() {
    return family;
  }
  
  public byte[] qualifier() {
    return qualifier;
  }
  
  public byte[] value() {
    return value;
  }
  
  public long timestamp() {
    return timestamp;
  }
  
  @Override
  public String toString() {
    return new String(key) + " " + new String(family) + ":" + new String(qualifier) + " -> " + new String(value);
  }
}
