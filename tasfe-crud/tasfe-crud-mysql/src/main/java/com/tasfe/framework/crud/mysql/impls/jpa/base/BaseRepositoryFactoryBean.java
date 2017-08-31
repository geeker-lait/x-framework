package com.tasfe.framework.crud.mysql.impls.jpa.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * Created by Lait on 2017/8/6.
 */
public class BaseRepositoryFactoryBean<R extends JpaRepository<T, I>, T, I extends Serializable>
        extends JpaRepositoryFactoryBean<R, T, I> {

	@Override
	protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
		return new BaseRepositoryFactory(entityManager);
	}


	private static class BaseRepositoryFactory<T, I extends Serializable> extends JpaRepositoryFactory {

        private final EntityManager entityManager;

		public BaseRepositoryFactory(EntityManager entityManager) {
			super(entityManager);
            this.entityManager = entityManager;
		}

		@Override
        protected SimpleJpaRepository getTargetRepository(RepositoryInformation metadata) {
            return new BaseRepositoryImpl<T, I>((Class<T>) metadata.getDomainType(), entityManager);
        }

		@Override
		protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
			return BaseRepository.class;
		}

	}
}
