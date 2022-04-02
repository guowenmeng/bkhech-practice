// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: RequestProto.proto

package com.bkhech.home.practice.rpc.protobuf;

public final class RequestProto {
  private RequestProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface ReqProtocolOrBuilder extends
      // @@protoc_insertion_point(interface_extends:protocol.ReqProtocol)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>string reqMsg = 1;</code>
     * @return The reqMsg.
     */
    java.lang.String getReqMsg();
    /**
     * <code>string reqMsg = 1;</code>
     * @return The bytes for reqMsg.
     */
    com.google.protobuf.ByteString
        getReqMsgBytes();

    /**
     * <code>int64 requestId = 2;</code>
     * @return The requestId.
     */
    long getRequestId();

    /**
     * <code>int32 type = 3;</code>
     * @return The type.
     */
    int getType();
  }
  /**
   * Protobuf type {@code protocol.ReqProtocol}
   */
  public  static final class ReqProtocol extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:protocol.ReqProtocol)
      ReqProtocolOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use ReqProtocol.newBuilder() to construct.
    private ReqProtocol(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private ReqProtocol() {
      reqMsg_ = "";
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new ReqProtocol();
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private ReqProtocol(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
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
              java.lang.String s = input.readStringRequireUtf8();

              reqMsg_ = s;
              break;
            }
            case 16: {

              requestId_ = input.readInt64();
              break;
            }
            case 24: {

              type_ = input.readInt32();
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
      return com.bkhech.home.practice.rpc.protobuf.RequestProto.internal_static_protocol_ReqProtocol_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.bkhech.home.practice.rpc.protobuf.RequestProto.internal_static_protocol_ReqProtocol_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.bkhech.home.practice.rpc.protobuf.RequestProto.ReqProtocol.class, com.bkhech.home.practice.rpc.protobuf.RequestProto.ReqProtocol.Builder.class);
    }

    public static final int REQMSG_FIELD_NUMBER = 1;
    private volatile java.lang.Object reqMsg_;
    /**
     * <code>string reqMsg = 1;</code>
     * @return The reqMsg.
     */
    public java.lang.String getReqMsg() {
      java.lang.Object ref = reqMsg_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        reqMsg_ = s;
        return s;
      }
    }
    /**
     * <code>string reqMsg = 1;</code>
     * @return The bytes for reqMsg.
     */
    public com.google.protobuf.ByteString
        getReqMsgBytes() {
      java.lang.Object ref = reqMsg_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        reqMsg_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int REQUESTID_FIELD_NUMBER = 2;
    private long requestId_;
    /**
     * <code>int64 requestId = 2;</code>
     * @return The requestId.
     */
    public long getRequestId() {
      return requestId_;
    }

    public static final int TYPE_FIELD_NUMBER = 3;
    private int type_;
    /**
     * <code>int32 type = 3;</code>
     * @return The type.
     */
    public int getType() {
      return type_;
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
      if (!getReqMsgBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, reqMsg_);
      }
      if (requestId_ != 0L) {
        output.writeInt64(2, requestId_);
      }
      if (type_ != 0) {
        output.writeInt32(3, type_);
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (!getReqMsgBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, reqMsg_);
      }
      if (requestId_ != 0L) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(2, requestId_);
      }
      if (type_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(3, type_);
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
      if (!(obj instanceof com.bkhech.home.practice.rpc.protobuf.RequestProto.ReqProtocol)) {
        return super.equals(obj);
      }
      com.bkhech.home.practice.rpc.protobuf.RequestProto.ReqProtocol other = (com.bkhech.home.practice.rpc.protobuf.RequestProto.ReqProtocol) obj;

      if (!getReqMsg()
          .equals(other.getReqMsg())) return false;
      if (getRequestId()
          != other.getRequestId()) return false;
      if (getType()
          != other.getType()) return false;
      return unknownFields.equals(other.unknownFields);
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + REQMSG_FIELD_NUMBER;
      hash = (53 * hash) + getReqMsg().hashCode();
      hash = (37 * hash) + REQUESTID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getRequestId());
      hash = (37 * hash) + TYPE_FIELD_NUMBER;
      hash = (53 * hash) + getType();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.bkhech.home.practice.rpc.protobuf.RequestProto.ReqProtocol parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.bkhech.home.practice.rpc.protobuf.RequestProto.ReqProtocol parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.bkhech.home.practice.rpc.protobuf.RequestProto.ReqProtocol parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.bkhech.home.practice.rpc.protobuf.RequestProto.ReqProtocol parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.bkhech.home.practice.rpc.protobuf.RequestProto.ReqProtocol parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.bkhech.home.practice.rpc.protobuf.RequestProto.ReqProtocol parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.bkhech.home.practice.rpc.protobuf.RequestProto.ReqProtocol parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.bkhech.home.practice.rpc.protobuf.RequestProto.ReqProtocol parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.bkhech.home.practice.rpc.protobuf.RequestProto.ReqProtocol parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.bkhech.home.practice.rpc.protobuf.RequestProto.ReqProtocol parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.bkhech.home.practice.rpc.protobuf.RequestProto.ReqProtocol parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.bkhech.home.practice.rpc.protobuf.RequestProto.ReqProtocol parseFrom(
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
    public static Builder newBuilder(com.bkhech.home.practice.rpc.protobuf.RequestProto.ReqProtocol prototype) {
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
     * Protobuf type {@code protocol.ReqProtocol}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:protocol.ReqProtocol)
        com.bkhech.home.practice.rpc.protobuf.RequestProto.ReqProtocolOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.bkhech.home.practice.rpc.protobuf.RequestProto.internal_static_protocol_ReqProtocol_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.bkhech.home.practice.rpc.protobuf.RequestProto.internal_static_protocol_ReqProtocol_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.bkhech.home.practice.rpc.protobuf.RequestProto.ReqProtocol.class, com.bkhech.home.practice.rpc.protobuf.RequestProto.ReqProtocol.Builder.class);
      }

      // Construct using com.bkhech.home.practice.rpc.protobuf.RequestProto.ReqProtocol.newBuilder()
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
        reqMsg_ = "";

        requestId_ = 0L;

        type_ = 0;

        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.bkhech.home.practice.rpc.protobuf.RequestProto.internal_static_protocol_ReqProtocol_descriptor;
      }

      @java.lang.Override
      public com.bkhech.home.practice.rpc.protobuf.RequestProto.ReqProtocol getDefaultInstanceForType() {
        return com.bkhech.home.practice.rpc.protobuf.RequestProto.ReqProtocol.getDefaultInstance();
      }

      @java.lang.Override
      public com.bkhech.home.practice.rpc.protobuf.RequestProto.ReqProtocol build() {
        com.bkhech.home.practice.rpc.protobuf.RequestProto.ReqProtocol result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public com.bkhech.home.practice.rpc.protobuf.RequestProto.ReqProtocol buildPartial() {
        com.bkhech.home.practice.rpc.protobuf.RequestProto.ReqProtocol result = new com.bkhech.home.practice.rpc.protobuf.RequestProto.ReqProtocol(this);
        result.reqMsg_ = reqMsg_;
        result.requestId_ = requestId_;
        result.type_ = type_;
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
        if (other instanceof com.bkhech.home.practice.rpc.protobuf.RequestProto.ReqProtocol) {
          return mergeFrom((com.bkhech.home.practice.rpc.protobuf.RequestProto.ReqProtocol)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.bkhech.home.practice.rpc.protobuf.RequestProto.ReqProtocol other) {
        if (other == com.bkhech.home.practice.rpc.protobuf.RequestProto.ReqProtocol.getDefaultInstance()) return this;
        if (!other.getReqMsg().isEmpty()) {
          reqMsg_ = other.reqMsg_;
          onChanged();
        }
        if (other.getRequestId() != 0L) {
          setRequestId(other.getRequestId());
        }
        if (other.getType() != 0) {
          setType(other.getType());
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
        com.bkhech.home.practice.rpc.protobuf.RequestProto.ReqProtocol parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.bkhech.home.practice.rpc.protobuf.RequestProto.ReqProtocol) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private java.lang.Object reqMsg_ = "";
      /**
       * <code>string reqMsg = 1;</code>
       * @return The reqMsg.
       */
      public java.lang.String getReqMsg() {
        java.lang.Object ref = reqMsg_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          reqMsg_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string reqMsg = 1;</code>
       * @return The bytes for reqMsg.
       */
      public com.google.protobuf.ByteString
          getReqMsgBytes() {
        java.lang.Object ref = reqMsg_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          reqMsg_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string reqMsg = 1;</code>
       * @param value The reqMsg to set.
       * @return This builder for chaining.
       */
      public Builder setReqMsg(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        reqMsg_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string reqMsg = 1;</code>
       * @return This builder for chaining.
       */
      public Builder clearReqMsg() {
        
        reqMsg_ = getDefaultInstance().getReqMsg();
        onChanged();
        return this;
      }
      /**
       * <code>string reqMsg = 1;</code>
       * @param value The bytes for reqMsg to set.
       * @return This builder for chaining.
       */
      public Builder setReqMsgBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        reqMsg_ = value;
        onChanged();
        return this;
      }

      private long requestId_ ;
      /**
       * <code>int64 requestId = 2;</code>
       * @return The requestId.
       */
      public long getRequestId() {
        return requestId_;
      }
      /**
       * <code>int64 requestId = 2;</code>
       * @param value The requestId to set.
       * @return This builder for chaining.
       */
      public Builder setRequestId(long value) {
        
        requestId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int64 requestId = 2;</code>
       * @return This builder for chaining.
       */
      public Builder clearRequestId() {
        
        requestId_ = 0L;
        onChanged();
        return this;
      }

      private int type_ ;
      /**
       * <code>int32 type = 3;</code>
       * @return The type.
       */
      public int getType() {
        return type_;
      }
      /**
       * <code>int32 type = 3;</code>
       * @param value The type to set.
       * @return This builder for chaining.
       */
      public Builder setType(int value) {
        
        type_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 type = 3;</code>
       * @return This builder for chaining.
       */
      public Builder clearType() {
        
        type_ = 0;
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


      // @@protoc_insertion_point(builder_scope:protocol.ReqProtocol)
    }

    // @@protoc_insertion_point(class_scope:protocol.ReqProtocol)
    private static final com.bkhech.home.practice.rpc.protobuf.RequestProto.ReqProtocol DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.bkhech.home.practice.rpc.protobuf.RequestProto.ReqProtocol();
    }

    public static com.bkhech.home.practice.rpc.protobuf.RequestProto.ReqProtocol getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<ReqProtocol>
        PARSER = new com.google.protobuf.AbstractParser<ReqProtocol>() {
      @java.lang.Override
      public ReqProtocol parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new ReqProtocol(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<ReqProtocol> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<ReqProtocol> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public com.bkhech.home.practice.rpc.protobuf.RequestProto.ReqProtocol getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_protocol_ReqProtocol_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_protocol_ReqProtocol_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static final com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\022RequestProto.proto\022\010protocol\">\n\013ReqPro" +
      "tocol\022\016\n\006reqMsg\030\001 \001(\t\022\021\n\trequestId\030\002 \001(\003" +
      "\022\014\n\004type\030\003 \001(\005B5\n%com.bkhech.home.practi" +
      "ce.rpc.protobufB\014RequestProtob\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_protocol_ReqProtocol_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_protocol_ReqProtocol_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_protocol_ReqProtocol_descriptor,
        new java.lang.String[] { "ReqMsg", "RequestId", "Type", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
