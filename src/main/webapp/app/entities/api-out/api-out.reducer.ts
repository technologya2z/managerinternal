import axios from 'axios';
import { createAsyncThunk, isFulfilled, isPending, isRejected } from '@reduxjs/toolkit';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { IQueryParams, createEntitySlice, EntityState, serializeAxiosError } from 'app/shared/reducers/reducer.utils';
import { IApiOut, defaultValue } from 'app/shared/model/api-out.model';

const initialState: EntityState<IApiOut> = {
  loading: false,
  errorMessage: null,
  entities: [],
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

const apiUrl = 'api/api-outs';

// Actions

export const getEntities = createAsyncThunk('apiOut/fetch_entity_list', async ({ page, size, sort }: IQueryParams) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}&` : '?'}cacheBuster=${new Date().getTime()}`;
  return axios.get<IApiOut[]>(requestUrl);
});

export const searchEntitiesApiOut = createAsyncThunk(
  'apiIn/fetch_entity_list',
  async ({ page, size, sort, searchCriterials }: IQueryParams) => {
    let requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}&` : '?'}`;
    Object.keys(searchCriterials).map(function (key) {
      requestUrl += `${key}=${searchCriterials[key]}&`;
    });
    return axios.get<IApiOut[]>(requestUrl);
  }
);

export const getEntity = createAsyncThunk(
  'apiOut/fetch_entity',
  async (id: string | number) => {
    const requestUrl = `${apiUrl}/${id}`;
    return axios.get<IApiOut>(requestUrl);
  },
  { serializeError: serializeAxiosError }
);

export const createEntity = createAsyncThunk(
  'apiOut/create_entity',
  async (entity: IApiOut, thunkAPI) => {
    const result = await axios.post<IApiOut>(apiUrl, cleanEntity(entity));
    thunkAPI.dispatch(getEntities({}));
    return result;
  },
  { serializeError: serializeAxiosError }
);

export const searchEntities = createAsyncThunk(
  'apiOut/fetch_entity_list',
  async ({ page, size, sort, searchCriterials }: IQueryParams) => {
    let requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}&` : '?'}`;
    Object.keys(searchCriterials).map(function (key) {
      requestUrl += `${key}=${searchCriterials[key]}&`;
    });
    return axios.get<IApiOut[]>(requestUrl);
  }
);

export const updateEntity = createAsyncThunk(
  'apiOut/update_entity',
  async (entity: IApiOut, thunkAPI) => {
    const result = await axios.put<IApiOut>(`${apiUrl}/${entity.id}`, cleanEntity(entity));
    thunkAPI.dispatch(getEntities({}));
    return result;
  },
  { serializeError: serializeAxiosError }
);

export const partialUpdateEntity = createAsyncThunk(
  'apiOut/partial_update_entity',
  async (entity: IApiOut, thunkAPI) => {
    const result = await axios.patch<IApiOut>(`${apiUrl}/${entity.id}`, cleanEntity(entity));
    thunkAPI.dispatch(getEntities({}));
    return result;
  },
  { serializeError: serializeAxiosError }
);

export const deleteEntity = createAsyncThunk(
  'apiOut/delete_entity',
  async (id: string | number, thunkAPI) => {
    const requestUrl = `${apiUrl}/${id}`;
    const result = await axios.delete<IApiOut>(requestUrl);
    thunkAPI.dispatch(getEntities({}));
    return result;
  },
  { serializeError: serializeAxiosError }
);

// slice

export const ApiOutSlice = createEntitySlice({
  name: 'apiOut',
  initialState,
  extraReducers(builder) {
    builder
      .addCase(getEntity.fulfilled, (state, action) => {
        state.loading = false;
        state.entity = action.payload.data;
      })
      .addCase(deleteEntity.fulfilled, state => {
        state.updating = false;
        state.updateSuccess = true;
        state.entity = {};
      })
      .addMatcher(isFulfilled(getEntities, searchEntitiesApiOut), (state, action) => {
        const { data, headers } = action.payload;

        return {
          ...state,
          loading: false,
          entities: data,
          totalItems: parseInt(headers['x-total-count'], 10),
        };
      })
      .addMatcher(isFulfilled(createEntity, updateEntity, partialUpdateEntity), (state, action) => {
        state.updating = false;
        state.loading = false;
        state.updateSuccess = true;
        state.entity = action.payload.data;
      })
      .addMatcher(isPending(getEntities, getEntity, searchEntitiesApiOut), state => {
        state.errorMessage = null;
        state.updateSuccess = false;
        state.loading = true;
      })
      .addMatcher(isPending(createEntity, updateEntity, partialUpdateEntity, deleteEntity), state => {
        state.errorMessage = null;
        state.updateSuccess = false;
        state.updating = true;
      });
  },
});

export const { reset } = ApiOutSlice.actions;

// Reducer
export default ApiOutSlice.reducer;
