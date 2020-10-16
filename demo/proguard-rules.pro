-keepattributes Exceptions,InnerClasses,Signature,Deprecated,*Annotation*,EnclosingMethod

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
