package com.yp2048.repositories.data.local;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class ScanFaceLocalDataSource_Factory implements Factory<ScanFaceLocalDataSource> {
  @Override
  public ScanFaceLocalDataSource get() {
    return newInstance();
  }

  public static ScanFaceLocalDataSource_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ScanFaceLocalDataSource newInstance() {
    return new ScanFaceLocalDataSource();
  }

  private static final class InstanceHolder {
    private static final ScanFaceLocalDataSource_Factory INSTANCE = new ScanFaceLocalDataSource_Factory();
  }
}
