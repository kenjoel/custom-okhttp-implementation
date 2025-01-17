// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: pubsub.proto

package com.spotify;

public final class Pubsub {
  private Pubsub() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface SubscriptionOrBuilder extends
      // @@protoc_insertion_point(interface_extends:spotify.Subscription)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>optional string uri = 1;</code>
     * @return Whether the uri field is set.
     */
    boolean hasUri();
    /**
     * <code>optional string uri = 1;</code>
     * @return The uri.
     */
    java.lang.String getUri();
    /**
     * <code>optional string uri = 1;</code>
     * @return The bytes for uri.
     */
    com.google.protobuf.ByteString
        getUriBytes();

    /**
     * <code>optional int32 expiry = 2;</code>
     * @return Whether the expiry field is set.
     */
    boolean hasExpiry();
    /**
     * <code>optional int32 expiry = 2;</code>
     * @return The expiry.
     */
    int getExpiry();

    /**
     * <code>optional int32 status_code = 3;</code>
     * @return Whether the statusCode field is set.
     */
    boolean hasStatusCode();
    /**
     * <code>optional int32 status_code = 3;</code>
     * @return The statusCode.
     */
    int getStatusCode();
  }
  /**
   * Protobuf type {@code spotify.Subscription}
   */
  public static final class Subscription extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:spotify.Subscription)
      SubscriptionOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use Subscription.newBuilder() to construct.
    private Subscription(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private Subscription() {
      uri_ = "";
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new Subscription();
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private Subscription(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 10: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000001;
              uri_ = bs;
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              expiry_ = input.readInt32();
              break;
            }
            case 24: {
              bitField0_ |= 0x00000004;
              statusCode_ = input.readInt32();
              break;
            }
            default: {
              if (!parseUnknownField(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.spotify.Pubsub.internal_static_spotify_Subscription_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.spotify.Pubsub.internal_static_spotify_Subscription_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.spotify.Pubsub.Subscription.class, com.spotify.Pubsub.Subscription.Builder.class);
    }

    private int bitField0_;
    public static final int URI_FIELD_NUMBER = 1;
    private volatile java.lang.Object uri_;
    /**
     * <code>optional string uri = 1;</code>
     * @return Whether the uri field is set.
     */
    @java.lang.Override
    public boolean hasUri() {
      return ((bitField0_ & 0x00000001) != 0);
    }
    /**
     * <code>optional string uri = 1;</code>
     * @return The uri.
     */
    @java.lang.Override
    public java.lang.String getUri() {
      java.lang.Object ref = uri_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          uri_ = s;
        }
        return s;
      }
    }
    /**
     * <code>optional string uri = 1;</code>
     * @return The bytes for uri.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString
        getUriBytes() {
      java.lang.Object ref = uri_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        uri_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int EXPIRY_FIELD_NUMBER = 2;
    private int expiry_;
    /**
     * <code>optional int32 expiry = 2;</code>
     * @return Whether the expiry field is set.
     */
    @java.lang.Override
    public boolean hasExpiry() {
      return ((bitField0_ & 0x00000002) != 0);
    }
    /**
     * <code>optional int32 expiry = 2;</code>
     * @return The expiry.
     */
    @java.lang.Override
    public int getExpiry() {
      return expiry_;
    }

    public static final int STATUS_CODE_FIELD_NUMBER = 3;
    private int statusCode_;
    /**
     * <code>optional int32 status_code = 3;</code>
     * @return Whether the statusCode field is set.
     */
    @java.lang.Override
    public boolean hasStatusCode() {
      return ((bitField0_ & 0x00000004) != 0);
    }
    /**
     * <code>optional int32 status_code = 3;</code>
     * @return The statusCode.
     */
    @java.lang.Override
    public int getStatusCode() {
      return statusCode_;
    }

    private byte memoizedIsInitialized = -1;
    @java.lang.Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (((bitField0_ & 0x00000001) != 0)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, uri_);
      }
      if (((bitField0_ & 0x00000002) != 0)) {
        output.writeInt32(2, expiry_);
      }
      if (((bitField0_ & 0x00000004) != 0)) {
        output.writeInt32(3, statusCode_);
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) != 0)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, uri_);
      }
      if (((bitField0_ & 0x00000002) != 0)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, expiry_);
      }
      if (((bitField0_ & 0x00000004) != 0)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(3, statusCode_);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof com.spotify.Pubsub.Subscription)) {
        return super.equals(obj);
      }
      com.spotify.Pubsub.Subscription other = (com.spotify.Pubsub.Subscription) obj;

      if (hasUri() != other.hasUri()) return false;
      if (hasUri()) {
        if (!getUri()
            .equals(other.getUri())) return false;
      }
      if (hasExpiry() != other.hasExpiry()) return false;
      if (hasExpiry()) {
        if (getExpiry()
            != other.getExpiry()) return false;
      }
      if (hasStatusCode() != other.hasStatusCode()) return false;
      if (hasStatusCode()) {
        if (getStatusCode()
            != other.getStatusCode()) return false;
      }
      if (!unknownFields.equals(other.unknownFields)) return false;
      return true;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      if (hasUri()) {
        hash = (37 * hash) + URI_FIELD_NUMBER;
        hash = (53 * hash) + getUri().hashCode();
      }
      if (hasExpiry()) {
        hash = (37 * hash) + EXPIRY_FIELD_NUMBER;
        hash = (53 * hash) + getExpiry();
      }
      if (hasStatusCode()) {
        hash = (37 * hash) + STATUS_CODE_FIELD_NUMBER;
        hash = (53 * hash) + getStatusCode();
      }
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.spotify.Pubsub.Subscription parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.spotify.Pubsub.Subscription parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.spotify.Pubsub.Subscription parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.spotify.Pubsub.Subscription parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.spotify.Pubsub.Subscription parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.spotify.Pubsub.Subscription parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.spotify.Pubsub.Subscription parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.spotify.Pubsub.Subscription parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.spotify.Pubsub.Subscription parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.spotify.Pubsub.Subscription parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.spotify.Pubsub.Subscription parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.spotify.Pubsub.Subscription parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(com.spotify.Pubsub.Subscription prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @java.lang.Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code spotify.Subscription}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:spotify.Subscription)
        com.spotify.Pubsub.SubscriptionOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.spotify.Pubsub.internal_static_spotify_Subscription_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.spotify.Pubsub.internal_static_spotify_Subscription_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.spotify.Pubsub.Subscription.class, com.spotify.Pubsub.Subscription.Builder.class);
      }

      // Construct using com.spotify.Pubsub.Subscription.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        uri_ = "";
        bitField0_ = (bitField0_ & ~0x00000001);
        expiry_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        statusCode_ = 0;
        bitField0_ = (bitField0_ & ~0x00000004);
        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.spotify.Pubsub.internal_static_spotify_Subscription_descriptor;
      }

      @java.lang.Override
      public com.spotify.Pubsub.Subscription getDefaultInstanceForType() {
        return com.spotify.Pubsub.Subscription.getDefaultInstance();
      }

      @java.lang.Override
      public com.spotify.Pubsub.Subscription build() {
        com.spotify.Pubsub.Subscription result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public com.spotify.Pubsub.Subscription buildPartial() {
        com.spotify.Pubsub.Subscription result = new com.spotify.Pubsub.Subscription(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) != 0)) {
          to_bitField0_ |= 0x00000001;
        }
        result.uri_ = uri_;
        if (((from_bitField0_ & 0x00000002) != 0)) {
          result.expiry_ = expiry_;
          to_bitField0_ |= 0x00000002;
        }
        if (((from_bitField0_ & 0x00000004) != 0)) {
          result.statusCode_ = statusCode_;
          to_bitField0_ |= 0x00000004;
        }
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      @java.lang.Override
      public Builder clone() {
        return super.clone();
      }
      @java.lang.Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.setField(field, value);
      }
      @java.lang.Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return super.clearField(field);
      }
      @java.lang.Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return super.clearOneof(oneof);
      }
      @java.lang.Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return super.setRepeatedField(field, index, value);
      }
      @java.lang.Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.addRepeatedField(field, value);
      }
      @java.lang.Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.spotify.Pubsub.Subscription) {
          return mergeFrom((com.spotify.Pubsub.Subscription)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.spotify.Pubsub.Subscription other) {
        if (other == com.spotify.Pubsub.Subscription.getDefaultInstance()) return this;
        if (other.hasUri()) {
          bitField0_ |= 0x00000001;
          uri_ = other.uri_;
          onChanged();
        }
        if (other.hasExpiry()) {
          setExpiry(other.getExpiry());
        }
        if (other.hasStatusCode()) {
          setStatusCode(other.getStatusCode());
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      @java.lang.Override
      public final boolean isInitialized() {
        return true;
      }

      @java.lang.Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.spotify.Pubsub.Subscription parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.spotify.Pubsub.Subscription) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private java.lang.Object uri_ = "";
      /**
       * <code>optional string uri = 1;</code>
       * @return Whether the uri field is set.
       */
      public boolean hasUri() {
        return ((bitField0_ & 0x00000001) != 0);
      }
      /**
       * <code>optional string uri = 1;</code>
       * @return The uri.
       */
      public java.lang.String getUri() {
        java.lang.Object ref = uri_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            uri_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>optional string uri = 1;</code>
       * @return The bytes for uri.
       */
      public com.google.protobuf.ByteString
          getUriBytes() {
        java.lang.Object ref = uri_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          uri_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>optional string uri = 1;</code>
       * @param value The uri to set.
       * @return This builder for chaining.
       */
      public Builder setUri(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        uri_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional string uri = 1;</code>
       * @return This builder for chaining.
       */
      public Builder clearUri() {
        bitField0_ = (bitField0_ & ~0x00000001);
        uri_ = getDefaultInstance().getUri();
        onChanged();
        return this;
      }
      /**
       * <code>optional string uri = 1;</code>
       * @param value The bytes for uri to set.
       * @return This builder for chaining.
       */
      public Builder setUriBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        uri_ = value;
        onChanged();
        return this;
      }

      private int expiry_ ;
      /**
       * <code>optional int32 expiry = 2;</code>
       * @return Whether the expiry field is set.
       */
      @java.lang.Override
      public boolean hasExpiry() {
        return ((bitField0_ & 0x00000002) != 0);
      }
      /**
       * <code>optional int32 expiry = 2;</code>
       * @return The expiry.
       */
      @java.lang.Override
      public int getExpiry() {
        return expiry_;
      }
      /**
       * <code>optional int32 expiry = 2;</code>
       * @param value The expiry to set.
       * @return This builder for chaining.
       */
      public Builder setExpiry(int value) {
        bitField0_ |= 0x00000002;
        expiry_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 expiry = 2;</code>
       * @return This builder for chaining.
       */
      public Builder clearExpiry() {
        bitField0_ = (bitField0_ & ~0x00000002);
        expiry_ = 0;
        onChanged();
        return this;
      }

      private int statusCode_ ;
      /**
       * <code>optional int32 status_code = 3;</code>
       * @return Whether the statusCode field is set.
       */
      @java.lang.Override
      public boolean hasStatusCode() {
        return ((bitField0_ & 0x00000004) != 0);
      }
      /**
       * <code>optional int32 status_code = 3;</code>
       * @return The statusCode.
       */
      @java.lang.Override
      public int getStatusCode() {
        return statusCode_;
      }
      /**
       * <code>optional int32 status_code = 3;</code>
       * @param value The statusCode to set.
       * @return This builder for chaining.
       */
      public Builder setStatusCode(int value) {
        bitField0_ |= 0x00000004;
        statusCode_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 status_code = 3;</code>
       * @return This builder for chaining.
       */
      public Builder clearStatusCode() {
        bitField0_ = (bitField0_ & ~0x00000004);
        statusCode_ = 0;
        onChanged();
        return this;
      }
      @java.lang.Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      @java.lang.Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:spotify.Subscription)
    }

    // @@protoc_insertion_point(class_scope:spotify.Subscription)
    private static final com.spotify.Pubsub.Subscription DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.spotify.Pubsub.Subscription();
    }

    public static com.spotify.Pubsub.Subscription getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    @java.lang.Deprecated public static final com.google.protobuf.Parser<Subscription>
        PARSER = new com.google.protobuf.AbstractParser<Subscription>() {
      @java.lang.Override
      public Subscription parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new Subscription(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<Subscription> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<Subscription> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public com.spotify.Pubsub.Subscription getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_spotify_Subscription_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_spotify_Subscription_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\014pubsub.proto\022\007spotify\"@\n\014Subscription\022" +
      "\013\n\003uri\030\001 \001(\t\022\016\n\006expiry\030\002 \001(\005\022\023\n\013status_c" +
      "ode\030\003 \001(\005B\r\n\013com.spotify"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_spotify_Subscription_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_spotify_Subscription_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_spotify_Subscription_descriptor,
        new java.lang.String[] { "Uri", "Expiry", "StatusCode", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
