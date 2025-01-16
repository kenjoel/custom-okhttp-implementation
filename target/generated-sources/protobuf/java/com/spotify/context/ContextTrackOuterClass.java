// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: context_track.proto

package com.spotify.context;

public final class ContextTrackOuterClass {
  private ContextTrackOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface ContextTrackOrBuilder extends
      // @@protoc_insertion_point(interface_extends:spotify.player.proto.ContextTrack)
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
     * <code>optional string uid = 2;</code>
     * @return Whether the uid field is set.
     */
    boolean hasUid();
    /**
     * <code>optional string uid = 2;</code>
     * @return The uid.
     */
    java.lang.String getUid();
    /**
     * <code>optional string uid = 2;</code>
     * @return The bytes for uid.
     */
    com.google.protobuf.ByteString
        getUidBytes();

    /**
     * <code>optional bytes gid = 3;</code>
     * @return Whether the gid field is set.
     */
    boolean hasGid();
    /**
     * <code>optional bytes gid = 3;</code>
     * @return The gid.
     */
    com.google.protobuf.ByteString getGid();

    /**
     * <code>map&lt;string, string&gt; metadata = 4;</code>
     */
    int getMetadataCount();
    /**
     * <code>map&lt;string, string&gt; metadata = 4;</code>
     */
    boolean containsMetadata(
        java.lang.String key);
    /**
     * Use {@link #getMetadataMap()} instead.
     */
    @java.lang.Deprecated
    java.util.Map<java.lang.String, java.lang.String>
    getMetadata();
    /**
     * <code>map&lt;string, string&gt; metadata = 4;</code>
     */
    java.util.Map<java.lang.String, java.lang.String>
    getMetadataMap();
    /**
     * <code>map&lt;string, string&gt; metadata = 4;</code>
     */

    /* nullable */
java.lang.String getMetadataOrDefault(
        java.lang.String key,
        /* nullable */
java.lang.String defaultValue);
    /**
     * <code>map&lt;string, string&gt; metadata = 4;</code>
     */

    java.lang.String getMetadataOrThrow(
        java.lang.String key);
  }
  /**
   * Protobuf type {@code spotify.player.proto.ContextTrack}
   */
  public static final class ContextTrack extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:spotify.player.proto.ContextTrack)
      ContextTrackOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use ContextTrack.newBuilder() to construct.
    private ContextTrack(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private ContextTrack() {
      uri_ = "";
      uid_ = "";
      gid_ = com.google.protobuf.ByteString.EMPTY;
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new ContextTrack();
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.spotify.context.ContextTrackOuterClass.internal_static_spotify_player_proto_ContextTrack_descriptor;
    }

    @SuppressWarnings({"rawtypes"})
    @java.lang.Override
    protected com.google.protobuf.MapField internalGetMapField(
        int number) {
      switch (number) {
        case 4:
          return internalGetMetadata();
        default:
          throw new RuntimeException(
              "Invalid map field number: " + number);
      }
    }
    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.spotify.context.ContextTrackOuterClass.internal_static_spotify_player_proto_ContextTrack_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.spotify.context.ContextTrackOuterClass.ContextTrack.class, com.spotify.context.ContextTrackOuterClass.ContextTrack.Builder.class);
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

    public static final int UID_FIELD_NUMBER = 2;
    private volatile java.lang.Object uid_;
    /**
     * <code>optional string uid = 2;</code>
     * @return Whether the uid field is set.
     */
    @java.lang.Override
    public boolean hasUid() {
      return ((bitField0_ & 0x00000002) != 0);
    }
    /**
     * <code>optional string uid = 2;</code>
     * @return The uid.
     */
    @java.lang.Override
    public java.lang.String getUid() {
      java.lang.Object ref = uid_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          uid_ = s;
        }
        return s;
      }
    }
    /**
     * <code>optional string uid = 2;</code>
     * @return The bytes for uid.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString
        getUidBytes() {
      java.lang.Object ref = uid_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        uid_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int GID_FIELD_NUMBER = 3;
    private com.google.protobuf.ByteString gid_;
    /**
     * <code>optional bytes gid = 3;</code>
     * @return Whether the gid field is set.
     */
    @java.lang.Override
    public boolean hasGid() {
      return ((bitField0_ & 0x00000004) != 0);
    }
    /**
     * <code>optional bytes gid = 3;</code>
     * @return The gid.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getGid() {
      return gid_;
    }

    public static final int METADATA_FIELD_NUMBER = 4;
    private static final class MetadataDefaultEntryHolder {
      static final com.google.protobuf.MapEntry<
          java.lang.String, java.lang.String> defaultEntry =
              com.google.protobuf.MapEntry
              .<java.lang.String, java.lang.String>newDefaultInstance(
                  com.spotify.context.ContextTrackOuterClass.internal_static_spotify_player_proto_ContextTrack_MetadataEntry_descriptor, 
                  com.google.protobuf.WireFormat.FieldType.STRING,
                  "",
                  com.google.protobuf.WireFormat.FieldType.STRING,
                  "");
    }
    private com.google.protobuf.MapField<
        java.lang.String, java.lang.String> metadata_;
    private com.google.protobuf.MapField<java.lang.String, java.lang.String>
    internalGetMetadata() {
      if (metadata_ == null) {
        return com.google.protobuf.MapField.emptyMapField(
            MetadataDefaultEntryHolder.defaultEntry);
      }
      return metadata_;
    }

    public int getMetadataCount() {
      return internalGetMetadata().getMap().size();
    }
    /**
     * <code>map&lt;string, string&gt; metadata = 4;</code>
     */

    @java.lang.Override
    public boolean containsMetadata(
        java.lang.String key) {
      if (key == null) { throw new NullPointerException("map key"); }
      return internalGetMetadata().getMap().containsKey(key);
    }
    /**
     * Use {@link #getMetadataMap()} instead.
     */
    @java.lang.Override
    @java.lang.Deprecated
    public java.util.Map<java.lang.String, java.lang.String> getMetadata() {
      return getMetadataMap();
    }
    /**
     * <code>map&lt;string, string&gt; metadata = 4;</code>
     */
    @java.lang.Override

    public java.util.Map<java.lang.String, java.lang.String> getMetadataMap() {
      return internalGetMetadata().getMap();
    }
    /**
     * <code>map&lt;string, string&gt; metadata = 4;</code>
     */
    @java.lang.Override

    public java.lang.String getMetadataOrDefault(
        java.lang.String key,
        java.lang.String defaultValue) {
      if (key == null) { throw new NullPointerException("map key"); }
      java.util.Map<java.lang.String, java.lang.String> map =
          internalGetMetadata().getMap();
      return map.containsKey(key) ? map.get(key) : defaultValue;
    }
    /**
     * <code>map&lt;string, string&gt; metadata = 4;</code>
     */
    @java.lang.Override

    public java.lang.String getMetadataOrThrow(
        java.lang.String key) {
      if (key == null) { throw new NullPointerException("map key"); }
      java.util.Map<java.lang.String, java.lang.String> map =
          internalGetMetadata().getMap();
      if (!map.containsKey(key)) {
        throw new java.lang.IllegalArgumentException();
      }
      return map.get(key);
    }

    public static com.spotify.context.ContextTrackOuterClass.ContextTrack parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.spotify.context.ContextTrackOuterClass.ContextTrack parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.spotify.context.ContextTrackOuterClass.ContextTrack parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.spotify.context.ContextTrackOuterClass.ContextTrack parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.spotify.context.ContextTrackOuterClass.ContextTrack parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.spotify.context.ContextTrackOuterClass.ContextTrack parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.spotify.context.ContextTrackOuterClass.ContextTrack parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.spotify.context.ContextTrackOuterClass.ContextTrack parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.spotify.context.ContextTrackOuterClass.ContextTrack parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.spotify.context.ContextTrackOuterClass.ContextTrack parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.spotify.context.ContextTrackOuterClass.ContextTrack parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.spotify.context.ContextTrackOuterClass.ContextTrack parseFrom(
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
    public static Builder newBuilder(com.spotify.context.ContextTrackOuterClass.ContextTrack prototype) {
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
     * Protobuf type {@code spotify.player.proto.ContextTrack}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:spotify.player.proto.ContextTrack)
        com.spotify.context.ContextTrackOuterClass.ContextTrackOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.spotify.context.ContextTrackOuterClass.internal_static_spotify_player_proto_ContextTrack_descriptor;
      }

      @SuppressWarnings({"rawtypes"})
      protected com.google.protobuf.MapField internalGetMapField(
          int number) {
        switch (number) {
          case 4:
            return internalGetMetadata();
          default:
            throw new RuntimeException(
                "Invalid map field number: " + number);
        }
      }
      @SuppressWarnings({"rawtypes"})
      protected com.google.protobuf.MapField internalGetMutableMapField(
          int number) {
        switch (number) {
          case 4:
            return internalGetMutableMetadata();
          default:
            throw new RuntimeException(
                "Invalid map field number: " + number);
        }
      }
      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.spotify.context.ContextTrackOuterClass.internal_static_spotify_player_proto_ContextTrack_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.spotify.context.ContextTrackOuterClass.ContextTrack.class, com.spotify.context.ContextTrackOuterClass.ContextTrack.Builder.class);
      }

      // Construct using com.spotify.context.ContextTrackOuterClass.ContextTrack.newBuilder()
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
        uid_ = "";
        bitField0_ = (bitField0_ & ~0x00000002);
        gid_ = com.google.protobuf.ByteString.EMPTY;
        bitField0_ = (bitField0_ & ~0x00000004);
        internalGetMutableMetadata().clear();
        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.spotify.context.ContextTrackOuterClass.internal_static_spotify_player_proto_ContextTrack_descriptor;
      }

      @java.lang.Override
      public com.spotify.context.ContextTrackOuterClass.ContextTrack getDefaultInstanceForType() {
        return com.spotify.context.ContextTrackOuterClass.ContextTrack.getDefaultInstance();
      }

      @java.lang.Override
      public com.spotify.context.ContextTrackOuterClass.ContextTrack build() {
        com.spotify.context.ContextTrackOuterClass.ContextTrack result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public com.spotify.context.ContextTrackOuterClass.ContextTrack buildPartial() {
        com.spotify.context.ContextTrackOuterClass.ContextTrack result = new com.spotify.context.ContextTrackOuterClass.ContextTrack(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) != 0)) {
          to_bitField0_ |= 0x00000001;
        }
        result.uri_ = uri_;
        if (((from_bitField0_ & 0x00000002) != 0)) {
          to_bitField0_ |= 0x00000002;
        }
        result.uid_ = uid_;
        if (((from_bitField0_ & 0x00000004) != 0)) {
          to_bitField0_ |= 0x00000004;
        }
        result.gid_ = gid_;
        result.metadata_ = internalGetMetadata();
        result.metadata_.makeImmutable();
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

      private java.lang.Object uid_ = "";
      /**
       * <code>optional string uid = 2;</code>
       * @return Whether the uid field is set.
       */
      public boolean hasUid() {
        return ((bitField0_ & 0x00000002) != 0);
      }
      /**
       * <code>optional string uid = 2;</code>
       * @return The uid.
       */
      public java.lang.String getUid() {
        java.lang.Object ref = uid_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            uid_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>optional string uid = 2;</code>
       * @return The bytes for uid.
       */
      public com.google.protobuf.ByteString
          getUidBytes() {
        java.lang.Object ref = uid_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          uid_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>optional string uid = 2;</code>
       * @param value The uid to set.
       * @return This builder for chaining.
       */
      public Builder setUid(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        uid_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional string uid = 2;</code>
       * @return This builder for chaining.
       */
      public Builder clearUid() {
        bitField0_ = (bitField0_ & ~0x00000002);
        uid_ = getDefaultInstance().getUid();
        onChanged();
        return this;
      }
      /**
       * <code>optional string uid = 2;</code>
       * @param value The bytes for uid to set.
       * @return This builder for chaining.
       */
      public Builder setUidBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        uid_ = value;
        onChanged();
        return this;
      }

      private com.google.protobuf.ByteString gid_ = com.google.protobuf.ByteString.EMPTY;
      /**
       * <code>optional bytes gid = 3;</code>
       * @return Whether the gid field is set.
       */
      @java.lang.Override
      public boolean hasGid() {
        return ((bitField0_ & 0x00000004) != 0);
      }
      /**
       * <code>optional bytes gid = 3;</code>
       * @return The gid.
       */
      @java.lang.Override
      public com.google.protobuf.ByteString getGid() {
        return gid_;
      }
      /**
       * <code>optional bytes gid = 3;</code>
       * @param value The gid to set.
       * @return This builder for chaining.
       */
      public Builder setGid(com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        gid_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional bytes gid = 3;</code>
       * @return This builder for chaining.
       */
      public Builder clearGid() {
        bitField0_ = (bitField0_ & ~0x00000004);
        gid_ = getDefaultInstance().getGid();
        onChanged();
        return this;
      }

      private com.google.protobuf.MapField<
          java.lang.String, java.lang.String> metadata_;
      private com.google.protobuf.MapField<java.lang.String, java.lang.String>
      internalGetMetadata() {
        if (metadata_ == null) {
          return com.google.protobuf.MapField.emptyMapField(
              MetadataDefaultEntryHolder.defaultEntry);
        }
        return metadata_;
      }
      private com.google.protobuf.MapField<java.lang.String, java.lang.String>
      internalGetMutableMetadata() {
        onChanged();;
        if (metadata_ == null) {
          metadata_ = com.google.protobuf.MapField.newMapField(
              MetadataDefaultEntryHolder.defaultEntry);
        }
        if (!metadata_.isMutable()) {
          metadata_ = metadata_.copy();
        }
        return metadata_;
      }

      public int getMetadataCount() {
        return internalGetMetadata().getMap().size();
      }
      /**
       * <code>map&lt;string, string&gt; metadata = 4;</code>
       */

      @java.lang.Override
      public boolean containsMetadata(
          java.lang.String key) {
        if (key == null) { throw new NullPointerException("map key"); }
        return internalGetMetadata().getMap().containsKey(key);
      }
      /**
       * Use {@link #getMetadataMap()} instead.
       */
      @java.lang.Override
      @java.lang.Deprecated
      public java.util.Map<java.lang.String, java.lang.String> getMetadata() {
        return getMetadataMap();
      }
      /**
       * <code>map&lt;string, string&gt; metadata = 4;</code>
       */
      @java.lang.Override

      public java.util.Map<java.lang.String, java.lang.String> getMetadataMap() {
        return internalGetMetadata().getMap();
      }
      /**
       * <code>map&lt;string, string&gt; metadata = 4;</code>
       */
      @java.lang.Override

      public java.lang.String getMetadataOrDefault(
          java.lang.String key,
          java.lang.String defaultValue) {
        if (key == null) { throw new NullPointerException("map key"); }
        java.util.Map<java.lang.String, java.lang.String> map =
            internalGetMetadata().getMap();
        return map.containsKey(key) ? map.get(key) : defaultValue;
      }
      /**
       * <code>map&lt;string, string&gt; metadata = 4;</code>
       */
      @java.lang.Override

      public java.lang.String getMetadataOrThrow(
          java.lang.String key) {
        if (key == null) { throw new NullPointerException("map key"); }
        java.util.Map<java.lang.String, java.lang.String> map =
            internalGetMetadata().getMap();
        if (!map.containsKey(key)) {
          throw new java.lang.IllegalArgumentException();
        }
        return map.get(key);
      }

      public Builder clearMetadata() {
        internalGetMutableMetadata().getMutableMap()
            .clear();
        return this;
      }
      /**
       * <code>map&lt;string, string&gt; metadata = 4;</code>
       */

      public Builder removeMetadata(
          java.lang.String key) {
        if (key == null) { throw new NullPointerException("map key"); }
        internalGetMutableMetadata().getMutableMap()
            .remove(key);
        return this;
      }
      /**
       * Use alternate mutation accessors instead.
       */
      @java.lang.Deprecated
      public java.util.Map<java.lang.String, java.lang.String>
      getMutableMetadata() {
        return internalGetMutableMetadata().getMutableMap();
      }
      /**
       * <code>map&lt;string, string&gt; metadata = 4;</code>
       */
      public Builder putMetadata(
          java.lang.String key,
          java.lang.String value) {
        if (key == null) { throw new NullPointerException("map key"); }
        if (value == null) {
  throw new NullPointerException("map value");
}

        internalGetMutableMetadata().getMutableMap()
            .put(key, value);
        return this;
      }
      /**
       * <code>map&lt;string, string&gt; metadata = 4;</code>
       */

      public Builder putAllMetadata(
          java.util.Map<java.lang.String, java.lang.String> values) {
        internalGetMutableMetadata().getMutableMap()
            .putAll(values);
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


      // @@protoc_insertion_point(builder_scope:spotify.player.proto.ContextTrack)
    }

    // @@protoc_insertion_point(class_scope:spotify.player.proto.ContextTrack)
    private static final com.spotify.context.ContextTrackOuterClass.ContextTrack DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.spotify.context.ContextTrackOuterClass.ContextTrack();
    }

    public static com.spotify.context.ContextTrackOuterClass.ContextTrack getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    @java.lang.Deprecated public static final com.google.protobuf.Parser<ContextTrack>
        PARSER = new com.google.protobuf.AbstractParser<ContextTrack>() {
      @java.lang.Override
      public ContextTrack parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        Builder builder = newBuilder();
        try {
          builder.mergeFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          throw e.setUnfinishedMessage(builder.buildPartial());
        } catch (java.io.IOException e) {
          throw new com.google.protobuf.InvalidProtocolBufferException(
              e.getMessage()).setUnfinishedMessage(
                  builder.buildPartial());
        }
        return builder.buildPartial();
      }
    };

    public static com.google.protobuf.Parser<ContextTrack> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<ContextTrack> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public com.spotify.context.ContextTrackOuterClass.ContextTrack getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_spotify_player_proto_ContextTrack_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_spotify_player_proto_ContextTrack_fieldAccessorTable;
  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_spotify_player_proto_ContextTrack_MetadataEntry_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_spotify_player_proto_ContextTrack_MetadataEntry_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\023context_track.proto\022\024spotify.player.pr" +
      "oto\"\252\001\n\014ContextTrack\022\013\n\003uri\030\001 \001(\t\022\013\n\003uid" +
      "\030\002 \001(\t\022\013\n\003gid\030\003 \001(\014\022B\n\010metadata\030\004 \003(\01320." +
      "spotify.player.proto.ContextTrack.Metada" +
      "taEntry\032/\n\rMetadataEntry\022\013\n\003key\030\001 \001(\t\022\r\n" +
      "\005value\030\002 \001(\t:\0028\001B\027\n\023com.spotify.contextH" +
      "\002"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_spotify_player_proto_ContextTrack_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_spotify_player_proto_ContextTrack_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_spotify_player_proto_ContextTrack_descriptor,
        new java.lang.String[] { "Uri", "Uid", "Gid", "Metadata", });
    internal_static_spotify_player_proto_ContextTrack_MetadataEntry_descriptor =
      internal_static_spotify_player_proto_ContextTrack_descriptor.getNestedTypes().get(0);
    internal_static_spotify_player_proto_ContextTrack_MetadataEntry_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_spotify_player_proto_ContextTrack_MetadataEntry_descriptor,
        new java.lang.String[] { "Key", "Value", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
