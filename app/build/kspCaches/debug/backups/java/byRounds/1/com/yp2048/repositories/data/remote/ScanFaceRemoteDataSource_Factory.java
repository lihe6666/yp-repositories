package com.yp2048.repositories.data.remote;

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
public final class ScanFaceRemoteDataSource_Factory implements Factory<ScanFaceRemoteDataSource> {
  @Override
  public ScanFaceRemoteDataSource get() {
    return newInstance();
  }

  public static ScanFaceRemoteDataSource_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ScanFaceRemoteDataSource newInstance() {
    return new ScanFaceRemoteDataSource();
  }

  private static final class InstanceHolder {
    private static final ScanFaceRemoteDataSource_Factory INSTANCE = new ScanFaceRemoteDataSource_Factory();
  }
}
