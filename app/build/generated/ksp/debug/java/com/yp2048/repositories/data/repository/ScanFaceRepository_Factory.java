package com.yp2048.repositories.data.repository;

import com.yp2048.repositories.data.local.ScanFaceLocalDataSource;
import com.yp2048.repositories.data.remote.ScanFaceRemoteDataSource;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class ScanFaceRepository_Factory implements Factory<ScanFaceRepository> {
  private final Provider<ScanFaceRemoteDataSource> scanFaceRemoteDataSourceProvider;

  private final Provider<ScanFaceLocalDataSource> scanFaceLocalDataSourceProvider;

  public ScanFaceRepository_Factory(
      Provider<ScanFaceRemoteDataSource> scanFaceRemoteDataSourceProvider,
      Provider<ScanFaceLocalDataSource> scanFaceLocalDataSourceProvider) {
    this.scanFaceRemoteDataSourceProvider = scanFaceRemoteDataSourceProvider;
    this.scanFaceLocalDataSourceProvider = scanFaceLocalDataSourceProvider;
  }

  @Override
  public ScanFaceRepository get() {
    return newInstance(scanFaceRemoteDataSourceProvider.get(), scanFaceLocalDataSourceProvider.get());
  }

  public static ScanFaceRepository_Factory create(
      Provider<ScanFaceRemoteDataSource> scanFaceRemoteDataSourceProvider,
      Provider<ScanFaceLocalDataSource> scanFaceLocalDataSourceProvider) {
    return new ScanFaceRepository_Factory(scanFaceRemoteDataSourceProvider, scanFaceLocalDataSourceProvider);
  }

  public static ScanFaceRepository newInstance(ScanFaceRemoteDataSource scanFaceRemoteDataSource,
      ScanFaceLocalDataSource scanFaceLocalDataSource) {
    return new ScanFaceRepository(scanFaceRemoteDataSource, scanFaceLocalDataSource);
  }
}
